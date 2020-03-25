plugins {
    java
}

group = "com.github.kropp"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.wrapper {
    distributionType = Wrapper.DistributionType.BIN
    gradleVersion = "6.3"
    distributionUrl = "https://cache-redirector.jetbrains.com/services.gradle.org/distributions/gradle-$gradleVersion-bin.zip"
}
