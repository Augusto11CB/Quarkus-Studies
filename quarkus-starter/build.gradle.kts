plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.allopen") version "1.9.22"
    id("io.quarkus")
    id("jacoco")
    id("jacoco-report-aggregation")
    id("org.sonarqube") version "2.7"
}

repositories {
//  maven { url = uri("https://repo.intranet.aug/artifactory/gradle-all/") }
    mavenCentral()
    mavenLocal()
}

// buildscript {
//     repositories {
//         maven { url = uri("https://repo.intranet.aug/artifactory/gradle-all/") }
//     }
//     dependencies {
//         classpath("br.com.aug:aug-ci-gradle-plugin:1.1.6")
//     }
// }

// apply<plugin>("br.com.aug.ci.gradle.plugin")


val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project
val ktlint by configurations.creating

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:quarkus-amazon-services-bom:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("io.quarkus:quarkus-arc")
//    implementation("io.quarkus:quarkus-resteasy-reactive")
//    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
//    implementation("io.quarkus:quarkus-rest-client-reactive-kotlin-serialization")


    implementation("io.quarkiverse.amazonservices:quarkus-amazon-kms")
    implementation("software.amazon.awssdk:url-connection-client")
    implementation("io.quarkus:quarkus-hibernate-orm-panache-kotlin")
    implementation("io.quarkus:quarkus-smallrye-opentracing")
    implementation("io.quarkus:quarkus-smallrye-fault-tolerance")
    implementation("io.quarkus:quarkus-rest-client")
    implementation("io.quarkus:quarkus-rest-client-jackson")
    implementation("io.quarkus:quarkus-smallrye-health")

    implementation("io.quarkus:quarkus-jdbc-oracle")
    implementation("io.quarkus:quarkus-agroal")
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("io.quarkus:quarkus-cache")
    implementation("io.quarkus:quarkus-scheduler")
    implementation("io.quarkus:quarkus-redis-client")
    implementation("io.quarkus:quarkus-amazon-sqs:2.14.0.Final")

    implementation("stax:stax-api:1.0.1")
    implementation("javax.xml.bind:jaxb-api:2.3.0")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.0")
    implementation("org.zalando:logbook-core:2.14.0")
    implementation("org.zalando:logbook-jaxrs:2.14.0")

    implementation("software.amazon.awssdk:url-connection-client:2.18.9")
    implementation("software.amazon.awssdk:netty-nio-client:2.18.12")
    implementation("org.json:json:20230227")

    // -------- Test Dependencies --------

    testImplementation("io.quarkus:quarkus-junit5")
    // testImplementation("io.quarkus:quarkus-flyway")

    testImplementation("io.rest-assured:rest-assured")

    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
    testImplementation("io.mockk:mockk:1.13.2")
    testImplementation("io.kotest:kotest-runner-junit5:5.5.4")
    testImplementation("io.kotest:kotest-assertions-core:5.5.4")
    testImplementation("io.kotest:kotest-property:5.5.4")
    testImplementation("com.intuit.karate:karate-junit5:0.9.6")
    testImplementation("com.intuit.karate:karate-apache:0.9.6")
//     testImplementation("org.testcontainers:testcontainers:1.17.3")
//     testImplementation("org.testcontainers:junit-jupiter:1.17.3")
//     testImplementation("org.testcontainers:oracle-xe:1.17.3")
//     testImplementation("org.testcontainers:mockserver:1.17.6")
    testImplementation("org.mock-server:mockserver-netty:5.13.2")
//     testImplementation("org.springframework:spring-jdbc:5.3.24")
//     testImplementation("org.testcontainers:localstack:1.17.6")
    testImplementation("com.amazonaws:aws-java-sdk-sqs:1.12.362")
    testImplementation("net.masterthought:cucumber-reporting:5.3.1")

    ktlint("com.pinterest:ktlint:0.50.0")
}

group = "com.buenosdev"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}

allOpen {
    // annotation("javax.ws.rs.Path")
    // annotation("javax.enterprise.context.ApplicationScoped")
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.enterprise.context.ApplicationScoped")
    // annotation("jakarta.persistence.Entity")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    kotlinOptions.javaParameters = true
}

tasks.compileKotlin {
    kotlinOptions.jvmTarget = "17"
    kotlinOptions.javaParameters = true
}

tasks.compileTestKotlin {
    kotlinOptions.jvmTarget = "17"
}

val ktlintCheck by tasks.registering(JavaExec::class) {
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    description = "Check Kotlin code style"
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    args(
        "**/src/**/*.kt",
        "**.kts",
        "!**/build/**",
    )
}

tasks.check {
    dependsOn(ktlintCheck)
}

tasks.register<JavaExec>("ktlintFormat") {
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    description = "Check Kotlin code style and format"
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    jvmArgs("--add-opens=java.base/java.lang=ALL-UNNAMED")
    args(
        "-F",
        "**/src/**/*.kt",
        "**.kts",
        "!**/build/**",
    )
}


/*

*/
/*****************************************************************************
 * JaCoCo Configuration
 *****************************************************************************//*


jacoco {
    toolVersion = "0.8.7"
}
jacocoTestReport {
    reports {
        csv.enabled = false
        xml.enabled = true
        html.enabled = true
        html.destination = file("${project.buildDir}/reports/jacoco/coverage")
        xml.destination = file("${project.buildDir}/reports/jacocoTestReport.xml")
    }
    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.collect {
            fileTree(dir: it, exclude: project.ext.exclusions)
        }))
    }
}

jacocoTestCoverageVerification {
    violationRules {
        rule {
            element = 'BUNDLE'
            limit {
                minimum = 0.00
                value = 'COVEREDRATIO'
                counter = 'INSTRUCTION'
            }
        }
    }
    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.collect {
            fileTree(dir: it, exclude: project.ext.exclusions)
        }))
    }
    dependsOn jacocoTestReport
}

*/


///////////////////////////


/*
*/
/*****************************************************************************
 * Sonar Configuration
 *****************************************************************************//*

sonarqube {
    properties {
        property "sonar.sourceEncoding", "UTF-8"
        property "sonar.projectKey", "quarkus-starter"
        property "sonar.java.source", "11"
        // property "sonar.host.url", "https://sonarqube.intranet.aug"
        property("sonar.projectKey", project.name)
        property("sonar.java.coveragePlugin", "jacoco")
        property("sonar.sources", "src/main/kotlin")
        property("sonar.tests", "src/test")
        property("sonar.junit.reportsPath", "${project.buildDir}/reports")
        property "sonar.jacoco.reportMissing.force.zero", "true"
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "${project.buildDir}/reports/jacocoTestReport.xml")
    }
}

check.dependsOn jacocoTestReport

*/
