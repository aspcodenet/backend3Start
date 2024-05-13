plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "se.systementor"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.yaml:snakeyaml")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv")
    implementation("com.rabbitmq:amqp-client")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.github.javafaker:javafaker:1.0.2") { exclude ("org.yaml") }
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.h2database:h2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

//val integrationTest: SourceSet = sourceSets.create("integrationTest") {
//    java {
//        compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
//        runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output
//    }
//    resources.srcDir("src/integration-test/resources")
//}
//
//configurations[integrationTest.implementationConfigurationName].extendsFrom(configurations.testImplementation.get())
//configurations[integrationTest.runtimeOnlyConfigurationName].extendsFrom(configurations.testRuntimeOnly.get())

val integrationTestTask = tasks.register<Test>("integrationTest") {
    group = "verification"
    filter {
        includeTestsMatching("*IT")
    }
}

tasks.test{
    filter{
        includeTestsMatching("*Tests")

    }
}

tasks.check {
    dependsOn(integrationTestTask)
}

