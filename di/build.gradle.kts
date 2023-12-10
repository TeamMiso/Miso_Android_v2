plugins {
    id(ProjectProperties.Gradle.KOTLIN)
    id(ProjectProperties.Gradle.APPLICATION)
    id(ProjectProperties.Gradle.HILT)
    kotlin(ProjectProperties.Gradle.KAPT)
}

android {
    namespace = ProjectProperties.Gradle.DI
    compileSdk = ProjectProperties.Versions.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = ProjectProperties.Gradle.DI
        minSdk = ProjectProperties.Versions.MIN_SDK_VERSION
        targetSdk = ProjectProperties.Versions.TARGET_SDK_VERSION
        versionCode = ProjectProperties.Versions.VERSION_CODE
        versionName = ProjectProperties.Versions.VERSION_NAME

        testInstrumentationRunner = ProjectProperties.TestProperties.TEST_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(ProjectProperties.Files.DEFAULT_PROGUARDFILES),
                ProjectProperties.Files.PROGUARDFILES
            )
        }
    }
    compileOptions {
        sourceCompatibility = ProjectProperties.Versions.JAVA_VERSION
        targetCompatibility = ProjectProperties.Versions.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = ProjectProperties.Versions.JVM_TARGET
    }
}

dependencies {
    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))

    //hilt
    implementation(Dependency.Google.HILT_ANDROID)
    kapt(Dependency.Google.HILT_ANDROID_COMPILER)

    //androidx
    implementation(Dependency.AndroidX.CORE_KTX)
    implementation(Dependency.AndroidX.APP_COMPAT)
    implementation(Dependency.AndroidX.PREFERENCE_KTX)

    //retrofit
    implementation(Dependency.Libraries.RETROFIT)
    implementation(Dependency.Libraries.RETROFIT_CONVERTER_GSON)

    //okhttp
    implementation(Dependency.Libraries.OKHTTP)
    implementation(Dependency.Libraries.OKHTTP_LOGGING_INTERCEPTOR)

    //unitTest
    testImplementation(Dependency.Test.JUNIT)

    //androidTest
    androidTestImplementation(Dependency.Test.ANDROID_JUNIT)
    androidTestImplementation(Dependency.Test.ESPRESSO_CORE)

    //dataStore
    implementation(Dependency.DataStore.PREFERENCES)
}