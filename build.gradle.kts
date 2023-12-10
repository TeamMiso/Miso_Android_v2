buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id(ProjectProperties.Gradle.APPLICATION) version Versions.GRADLE_ANDROID apply false
    id(ProjectProperties.Gradle.LIBRARY) version Versions.GRADLE_ANDROID apply false
    id(ProjectProperties.Gradle.KOTLIN) version Versions.GRADLE_KOTLIN apply false
    id(ProjectProperties.Gradle.HILT) version Versions.HILT apply false
}