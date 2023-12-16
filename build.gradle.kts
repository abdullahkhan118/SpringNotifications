import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.google.firebase:firebase-admin:9.2.0")
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

//
//plugins {
//	id("java")
//	id("org.springframework.boot") version "3.1.5"
//	id("io.spring.dependency-management")
//	kotlin("jvm")
//	kotlin("plugin.spring")
//	kotlin("kapt")
//}
//
//group = "com.horux"
//version = "0.0.1-SNAPSHOT"
//
//java {
//	sourceCompatibility = JavaVersion.VERSION_17
//}
//
//configurations {
//	compileOnly {
//		extendsFrom(configurations.annotationProcessor.get())
//	}
//}
//
//repositories {
//	mavenCentral()
//}
//
//dependencies {
//	implementation("org.springframework.boot:spring-boot-starter-web")
//	// https://mvnrepository.com/artifact/com.google.firebase/firebase-admin
//	implementation("com.google.firebase:firebase-admin:9.2.0")
//
//}
//
//tasks.test {
//	useJUnitPlatform()
//}