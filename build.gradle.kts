import java.net.URI

repositories {
    mavenLocal()
    mavenCentral()
    maven(url = URI("https://packages.atlassian.com/maven-external/"))
}

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.70")
    }
}

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.2.70"
}

dependencies {
    compile("com.atlassian.performance.tools:jira-performance-tests:[3.0.0,4.0.0)")
}
