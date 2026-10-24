plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.mymenuproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mymenuproject"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.robolectric:robolectric:4.13")
    testImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

tasks.withType<Test>().configureEach {
    // Pass -Pcafe.screenshots=<dir> to save a PNG of every screen from CafeFlowTest.
    project.findProperty("cafe.screenshots")?.let { systemProperty("cafe.screenshots", it) }
    // Lets Robolectric fetch its Android runtime from a Maven Central mirror when needed.
    project.findProperty("robolectric.repo")?.let { systemProperty("robolectric.dependency.repo.url", it) }
}
