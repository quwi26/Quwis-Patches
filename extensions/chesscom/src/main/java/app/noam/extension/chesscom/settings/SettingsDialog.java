package app.noam.extension.chesscom.settings;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import app.noam.extension.chesscom.Utils;

/**
 * The Quwi's Patches screen in a dialog. A root mount bind-mounts the patched APK over the stock
 * app, but the system keeps the manifest it parsed from the stock APK, so an activity the patch
 * adds is never declared and cannot be started; a dialog needs no entry in the manifest.
 */
final class SettingsDialog extends Dialog {
    private SettingsScreen screen;
    /** The activity under the dialog: it goes away with that activity, not after it. */
    private Activity owner;
    private Application.ActivityLifecycleCallbacks lifecycle;

    private SettingsDialog(Activity activity) {
        super(activity);
    }

    /** Shows the screen over the activity in front; nothing needs to be in the manifest. */
    static boolean show(Context context, String page) {
        Activity activity = context instanceof Activity ? (Activity) context : Utils.topActivity();
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            Utils.toast("Couldn't open that screen. Try again from the More tab.");
            return false;
        }
        try {
            SettingsDialog dialog = new SettingsDialog(activity);
            dialog.open(page);
            return true;
        } catch (Throwable throwable) {
            Utils.logError("Could not show the settings dialog", throwable);
            Utils.toast("Couldn't open that screen. Try again from the More tab.");
            return false;
        }
    }

    private void open(String page) {
        screen = new SettingsScreen(getContext());
        screen.onClose(this::dismiss);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setDimAmount(0.6f);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        screen.window(window);
        setContentView(screen.create(page, 0));
        setOnKeyListener((dialog, keyCode, event) -> {
            if (keyCode != KeyEvent.KEYCODE_BACK || event.getAction() != KeyEvent.ACTION_UP) return false;
            if (!screen.goBack()) dismiss();
            return true;
        });
        watchOwner();
        show();
    }

    /** Dismisses the dialog when the activity it is shown over goes away. */
    private void watchOwner() {
        owner = (Activity) getContext();
        lifecycle = new Application.ActivityLifecycleCallbacks() {
            @Override public void onActivityCreated(Activity activity, Bundle state) {}
            @Override public void onActivityStarted(Activity activity) {}
            @Override public void onActivityResumed(Activity activity) {}
            @Override public void onActivityPaused(Activity activity) {}
            @Override public void onActivityStopped(Activity activity) {}
            @Override public void onActivitySaveInstanceState(Activity activity, Bundle state) {}
            @Override public void onActivityDestroyed(Activity activity) {
                if (activity == owner) dismiss();
            }
        };
        owner.getApplication().registerActivityLifecycleCallbacks(lifecycle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (screen != null) screen.resume();
    }

    @Override
    protected void onStop() {
        if (screen != null) screen.pause();
        super.onStop();
    }

    @Override
    public void dismiss() {
        if (lifecycle != null && owner != null) {
            owner.getApplication().unregisterActivityLifecycleCallbacks(lifecycle);
            lifecycle = null;
        }
        if (screen != null) {
            screen.destroy();
            screen = null;
        }
        try {
            super.dismiss();
        } catch (Throwable throwable) {
            Utils.logError("Could not dismiss the settings dialog", throwable);
        }
    }
}
