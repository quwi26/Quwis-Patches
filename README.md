<h1 align="center">Noam's Morphe Patches Stockfish+</h1>

<p align="center">
  A fork of <a href="https://github.com/ImNoammm/Noams-Morphe-Patches">Noam's Morphe Patches</a> for chess.com only,
  with a fourth Game Review option: Stockfish on my laptop.
</p>

<p align="center">
  <a href="https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus/releases/latest"><img alt="Latest release" src="https://img.shields.io/github/v/release/quwi26/Noams-Morphe-Patches-StockfishPlus?label=release"></a>
  <img alt="Morphe patches" src="https://img.shields.io/badge/Morphe-patches-3DDC84?logo=android&logoColor=white">
  <a href="LICENSE"><img alt="GPL-3.0" src="https://img.shields.io/badge/license-GPL--3.0-lightgrey"></a>
</p>

Add this source to [Morphe](https://morphe.software) Manager once:

```
https://raw.githubusercontent.com/quwi26/Noams-Morphe-Patches-StockfishPlus/main/patches-bundle.json
```

## What changed from Noam's

- **Game Review gets a fourth reviewer: “Stockfish on my laptop.”** Instead of searching on the phone, the
  review is searched by [`sf-bridge`](#stockfish-on-my-laptop) on your computer over the local network: the same
  Stockfish 19, on all of that machine's threads, and the same Lichess-style marks come back. A review that took
  a minute on the phone takes seconds; the node budget per move (Fast 100k, Standard 300k, Deep 1M, Maximum 5M)
  is the one from the settings, exactly as on the phone.
- The patches for other apps (Block Blast, Gboard, Geocaching) are **not** in this bundle.

Everything else is Noam's work, unchanged: chess.com 4.10.18 with arrows in games, board and accent colours,
AMOLED black, the website's Arcade animations, custom Home and bottom bar, time controls under a minute,
offline Stockfish analysis on the phone, Lichess reviews and offline Lichess puzzles, every bot unlocked,
no daily Play Coach limit, Premium content, no ads or rating prompts — all switched on and off under
**More → Noam's Patches**.

## Stockfish on my laptop

The laptop runs `sf-bridge`, which starts a Stockfish per connection and passes UCI through a plain TCP socket:

```
server: sf-bridge 1 cores 12
client: auth <token>
server: auth ok
... plain UCI from there
```

Then in the app: **More → Noam's Patches → Game Review → Stockfish on my laptop**, and fill in

- **Laptop address** — `192.168.0.101:7892` (the laptop's address on the same Wi-Fi);
- **Token** — the one sf-bridge prints when it starts (`token.txt` next to it);
- **Laptop Stockfish threads** — Auto uses every core the laptop reports, with 32 MB of hash each.

Press **Test the connection**; it should answer “The laptop answered: its Stockfish is ready.” The review
chooser then offers **Stockfish on my laptop** next to chess.com, Local Stockfish and Lichess.

The bridge lives outside this repository (Node.js + `stockfish.exe`, `server.js`, `README.md` with the firewall
rule for TCP 7892). Nothing is sent anywhere else: the phone talks to your laptop on your network only.

## Credits

All patches are [Noam's](https://github.com/ImNoammm/Noams-Morphe-Patches) (GPL-3.0), which itself builds on
PrathxmOp's work; the laptop reviewer was added in this fork. If you want the patches for other apps too,
use Noam's own source.

## Building

```
./gradlew :patches:generatePatchesList   # patch list, then...
./gradlew :patches:buildAndroid          # ...the bundle with classes.dex (assemble alone has no dex)
```

The bundle lands in `patches/build/libs/patches-<version>.mpp`. Building needs JDK 21+, the Android SDK
(`ANDROID_HOME`), and GitHub Packages credentials for the Morphe plugin (`gpr.user` / `gpr.key` in
`~/.gradle/gradle.properties`).
