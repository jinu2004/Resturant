import org.jetbrains.compose.desktop.application.dsl.TargetFormat


val mongoVersion: String by project
plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.9.0"
}

group = "com.cloudliber"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}


dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-kernel:4.7.3")
    implementation("org.jetbrains.compose.material3:material3-desktop:1.5.0")
    implementation("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("io.insert-koin:koin-core:3.5.3")
    implementation("io.insert-koin:koin-compose-viewmodel:1.2.0-Beta4")
    implementation("io.insert-koin:koin-logger-slf4j:3.5.3")
    implementation("io.insert-koin:koin-compose:1.1.5")
    implementation("org.mongodb:mongodb-driver-core:$mongoVersion")
    implementation("org.mongodb:mongodb-driver-sync:$mongoVersion")
    implementation("org.mongodb:bson:$mongoVersion")
    implementation("org.litote.kmongo:kmongo:$mongoVersion")
    implementation("org.litote.kmongo:kmongo-coroutine:$mongoVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("com.darkrockstudios:mpfilepicker:2.1.0")

}



compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "RestaurantManagement"
            packageVersion = "1.0.0"
        }
    }
}
