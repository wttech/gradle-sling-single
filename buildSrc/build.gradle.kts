plugins {
    `kotlin-dsl`
}

repositories {
    mavenLocal()
    gradlePluginPortal()
    jcenter()
    maven { url = uri("http://dl.bintray.com/cognifide/maven-public") }
    maven { url = uri("https://dl.bintray.com/neva-dev/maven-public") }
}

dependencies {
    implementation("com.cognifide.gradle:aem-plugin:10.0.0")
    implementation("com.neva.gradle:fork-plugin:4.1.3")
}
