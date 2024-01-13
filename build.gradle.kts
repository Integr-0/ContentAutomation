import java.net.URI

plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "net.integr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = URI("https://jitpack.io") }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.squareup.okhttp:okhttp:2.7.5")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.github.javafaker:javafaker:1.0.2")
    implementation("io.github.ikfly:java-tts:1.0.2")
    implementation("net.bramp.ffmpeg:ffmpeg:0.8.0")
    implementation("org.slf4j:slf4j-nop:2.0.7")
    implementation("org.seleniumhq.selenium:selenium-java:4.16.1")


}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}