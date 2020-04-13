plugins {
    id("org.springframework.boot") version "2.2.0.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("jvm") version "1.3.71"
    kotlin("plugin.spring") version "1.3.50"
}

group = "com.dream365"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    //Spring Quartz
    implementation("org.springframework:spring-context-support")
    implementation("org.quartz-scheduler:quartz:2.3.0")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
