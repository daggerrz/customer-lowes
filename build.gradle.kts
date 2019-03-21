
allprojects {
    group = "com.motiion.customer.lowes"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}


plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    id("org.jetbrains.kotlin.jvm").version("1.3.21")
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.apache.daffodil:daffodil-japi_2.12:2.2.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}
