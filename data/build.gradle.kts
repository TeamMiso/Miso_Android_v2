import java.io.FileInputStream
import java.util.Properties

plugins {
    id(ProjectProperties.Gradle.KOTLIN)
    id(ProjectProperties.Gradle.LIBRARY)
    id(ProjectProperties.Gradle.HILT)
    kotlin(ProjectProperties.Gradle.KAPT)
}

android {
    namespace = ProjectProperties.Gradle.DATA
    compileSdk = ProjectProperties.Versions.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = ProjectProperties.Versions.MIN_SDK_VERSION

        testInstrumentationRunner = ProjectProperties.TestProperties.TEST_RUNNER
        consumerProguardFiles(ProjectProperties.Files.CONSUMER_PROGUARDFILES)

        buildConfigField(
            "String",
            "BASE_URL",
            getApiKey("BASE_URL")
        )
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
    implementation(project(":domain"))

    //hilt
    implementation(Dependency.Google.HILT_ANDROID)
    kapt(Dependency.Google.HILT_ANDROID_COMPILER)

    //unitTest
    testImplementation(Dependency.Test.JUNIT)

    //androidTest
    androidTestImplementation(Dependency.Test.ANDROID_JUNIT)
    androidTestImplementation(Dependency.Test.ESPRESSO_CORE)

    //retrofit
    implementation(Dependency.Libraries.RETROFIT)
    implementation(Dependency.Libraries.RETROFIT_CONVERTER_GSON)

    //okhttp
    implementation(Dependency.Libraries.OKHTTP)
    implementation(Dependency.Libraries.OKHTTP_LOGGING_INTERCEPTOR)

    //preference
    implementation(Dependency.DataStore.PREFERENCES)
}

fun getApiKey(propertyKey: String): String {
    val propFile = rootProject.file("./local.properties")
    val properties = Properties()
    properties.load(FileInputStream(propFile))
    return properties.getProperty(propertyKey)
}