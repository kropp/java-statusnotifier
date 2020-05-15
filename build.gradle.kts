plugins {
  java
  `maven-publish`
}

repositories {
  mavenCentral()
  maven(url = "https://jitpack.io")
}

group = "com.github.kropp"
version = "0.1.4"

dependencies {
  implementation("com.github.hypfvieh:dbus-java:master")
  val slf4jVersion = "1.7.26"
  implementation("org.slf4j:slf4j-api:$slf4jVersion")
  implementation("org.slf4j:slf4j-simple:$slf4jVersion")

  testImplementation("org.junit.jupiter:junit-jupiter:5.6.0")
}

configure<JavaPluginConvention> {
  sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.wrapper {
  distributionType = Wrapper.DistributionType.BIN
  gradleVersion = "6.3"
  distributionUrl = "https://services.gradle.org/distributions/gradle-$gradleVersion-bin.zip"
}
