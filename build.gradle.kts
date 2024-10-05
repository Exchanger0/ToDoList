import java.net.URI

plugins {
    id("java")
    id("org.openjfx.javafxplugin") version "0.1.0"
    application
}

application.mainClass = "com.todolist.App"

group = "com.todolist"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

javafx {
    version = "22.0.1"
    modules = listOf("javafx.controls", "javafx.base", "javafx.fxml", "javafx.graphics")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}