rootProject.name = "composite-build-repro"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

includeBuild("infrastructure")
