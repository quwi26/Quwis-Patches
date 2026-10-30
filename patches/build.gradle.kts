group = "app.noam"

patches {
    about {
        name = "Noam's Morphe Patches Stockfish+"
        description = "Noam's Morphe patches, plus Game Review on the Stockfish of my laptop."
        source = "git@github.com:quwi26/Noams-Morphe-Patches-StockfishPlus.git"
        author = "ImNoammm, quwi26"
        contact = "na"
        website = "https://github.com/quwi26/Noams-Morphe-Patches-StockfishPlus"
        license = "GNU General Public License v3.0"
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

// Separate configuration so gson is available at runtime for the
// generatePatchesList task but never bundled into the APK.
val patchListGeneratorClasspath = configurations.create("patchListGeneratorClasspath")

dependencies {
    compileOnly(libs.gson)
    patchListGeneratorClasspath(libs.gson)
}

tasks {
    register<JavaExec>("generatePatchesList") {
        description = "Build patch with patch list"

        dependsOn(build)

        classpath = sourceSets["main"].runtimeClasspath + patchListGeneratorClasspath
        mainClass.set("util.PatchListGeneratorKt")
    }

    publish {
        dependsOn("generatePatchesList")
    }
}
