buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id(ProjectProperties.Gradle.APPLICATION) version "8.1.4" apply false
    id(ProjectProperties.Gradle.KOTLIN) version "1.8.10" apply false
    id(ProjectProperties.Gradle.LIBRARY) version "8.1.4" apply false
}