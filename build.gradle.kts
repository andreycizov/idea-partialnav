plugins {
  id("java")
  id("org.jetbrains.intellij") version "1.5.2"
}

group "com.andreycizov.partialnav"
version = "2.22"

repositories {
  mavenCentral()
}

// Configure Gradle IntelliJ Plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
intellij {
  version.set("LATEST-EAP-SNAPSHOT")
  type.set("IC") // Target IDE Platform

  plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
  // Set the JVM compatibility versions
  withType<JavaCompile> {
    sourceCompatibility = "11"
    targetCompatibility = "11"
  }

  patchPluginXml {
    sinceBuild.set("212")
    untilBuild.set("222.*")

    // TODO Not sure this works! But leaving to retain old code
    changeNotes.value("""
      <ul>
      <li>Version 2.2: Converted to Kotlin Gradle and fixed issues to work with 2022.2</li>
      <li>Version 1.2: honour "Prefer moving caret line to minimize editor scrolling" and support navigation by a fixed number of lines</li>
      <li>Version 1.1: support partial page up/down with selection, set default keyboard shortcuts</li>
      <li>Version 1.0: build the plugin for the first time</li>
      </ul>
      """)
  }

  signPlugin {
    certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
    privateKey.set(System.getenv("PRIVATE_KEY"))
    password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
  }

  publishPlugin {
    token.set(System.getenv("PUBLISH_TOKEN"))
  }
}



