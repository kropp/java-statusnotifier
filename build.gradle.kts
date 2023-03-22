plugins {
  java
  `maven-publish`
}

repositories {
  mavenCentral()
  maven(url = "https://jitpack.io")
}

group = "com.github.kropp"
version = "0.3.4"

dependencies {
  implementation("com.github.hypfvieh:dbus-java-core:4.0.0")
  implementation("com.github.hypfvieh:dbus-java-transport-native-unixsocket:4.0.0")

  val slf4jVersion = "1.7.36"
  implementation("org.slf4j:slf4j-api:$slf4jVersion")

  val jnaVersion = "5.11.0"
  implementation("net.java.dev.jna:jna:$jnaVersion")
  implementation("net.java.dev.jna:jna-platform:$jnaVersion")

  testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

tasks.withType<JavaCompile> {
  sourceCompatibility = "17"
}

tasks.wrapper {
  distributionType = Wrapper.DistributionType.BIN
  gradleVersion = "7.4.2"
  distributionUrl = "https://services.gradle.org/distributions/gradle-$gradleVersion-bin.zip"
}

publishing {
  publications {
    create<MavenPublication>("Maven") {
      from(components["java"])
    }
  }
}
