val intellijPublishToken: String by project

plugins {
    id("org.jetbrains.intellij") version "0.4.21"
    java
    kotlin("jvm") version "1.3.72"
}

version = "3.8.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = "2020.2.2"
    setPlugins("java")
    pluginName = "SmartTomcat"
    updateSinceUntilBuild = false
}
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
}
tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    sinceBuild("193")
    changeNotes("""
      <ul>
      <li>fixed no special module with re-open project</li>
     </ul>
      """)
}

tasks.getByName<org.jetbrains.intellij.tasks.PublishTask>("publishPlugin") {
    token(intellijPublishToken)
}
