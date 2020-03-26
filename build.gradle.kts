plugins {
  java
  `maven-publish`
}

repositories {
  maven(url = "https://jitpack.io")
}

group = "com.github.kropp"
version = "0.1"

repositories {
  mavenCentral()
}

dependencies {
  implementation("com.github.hypfvieh:dbus-java:master")

  testImplementation("org.junit.jupiter:junit-jupiter:5.6.0")
}

configure<JavaPluginConvention> {
  sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.wrapper {
  distributionType = Wrapper.DistributionType.BIN
  gradleVersion = "6.3"
  distributionUrl = "https://cache-redirector.jetbrains.com/services.gradle.org/distributions/gradle-$gradleVersion-bin.zip"
}
