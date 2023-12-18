plugins {
    id(ProjectProperties.Gradle.KOTLIN)
    id(ProjectProperties.Gradle.LIBRARY)
    id(ProjectProperties.Gradle.HILT)
    kotlin(ProjectProperties.Gradle.KAPT)
}

android {
    namespace = ProjectProperties.Gradle.PERSENTATION
    compileSdk = ProjectProperties.Versions.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = ProjectProperties.Versions.MIN_SDK_VERSION

        testInstrumentationRunner = ProjectProperties.TestProperties.TEST_RUNNER
        consumerProguardFiles(ProjectProperties.Files.CONSUMER_PROGUARDFILES)
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":design-system"))

    //hilt
    implementation(Dependency.Google.HILT_ANDROID)
    kapt(Dependency.Google.HILT_ANDROID_COMPILER)

    //aac
    implementation(Dependency.AndroidX.APP_COMPAT)
    implementation(Dependency.AndroidX.CORE_KTX)
    implementation(Dependency.AndroidX.LIFECYCLE_RUNTIME)
    implementation(Dependency.Navigation.NAVIGATION)

    //camerax
    implementation(Dependency.AndroidX.CAMERA_CORE)
    implementation(Dependency.AndroidX.CAMERA_CAMERA2)
    implementation(Dependency.AndroidX.CAMERA_LIFECYCLE)
    implementation(Dependency.AndroidX.CAMERA_VIEW)
    implementation(Dependency.AndroidX.CAMERA_EXTENSIONS)
    
    //compose
    implementation(Dependency.Compose.ACTIVITY_COMPOSE)
    implementation(Dependency.Compose.COMPOSE)
    implementation(Dependency.Compose.COMPOSE_TOOLING)
    implementation(Dependency.Compose.COMPOSE_MATERIAL)
    implementation(Dependency.Compose.COMPOSE_MATERIAL3)
    implementation(Dependency.Compose.COMPOSE_PREVIEW)

    //junit
    testImplementation(Dependency.Test.JUNIT)
    androidTestImplementation(Dependency.Test.ANDROID_JUNIT)
    androidTestImplementation(Dependency.Test.ESPRESSO_CORE)
    androidTestImplementation(Dependency.Test.COMPOSE_JUNIT)
    debugImplementation(Dependency.Compose.COMPOSE_TOOLING)
    debugImplementation(Dependency.Test.COMPOSE_MANIFEST)

    //accompanist
    implementation(Dependency.Google.ACCOMPANIST)
    implementation(Dependency.Google.ACCOMPANIST_PERMISSION)

    //splash
    implementation(Dependency.AndroidX.SPLASH)

    //coil
    implementation(Dependency.Image.COIL)

    //lottie
    implementation(Dependency.Lottie.LOTTIE)
}