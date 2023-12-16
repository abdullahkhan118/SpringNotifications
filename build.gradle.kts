import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI

plugins {
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.20"
	kotlin("plugin.spring") version "1.9.20"
	id("maven-publish")
}

apply {
	plugin("maven-publish")
}

group = "com.github.abdullahkhan118"
version = "1.0.0"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
	maven { url = URI.create("https://jitpack.io") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.google.firebase:firebase-admin:9.2.0")
	implementation("com.github.abdullahkhan118:KotlinExtensions:1.0.8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

afterEvaluate {
	publishing {
		publications {
			create<MavenPublication>("release") {
				from(components["java"])
				groupId = "com.github.abdullahkhan118"
				artifactId = "kotlin.notifications"
				version = "1.0.0"
			}
		}
		repositories {
			mavenLocal()
		}
	}
}