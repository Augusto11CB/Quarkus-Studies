plugins {
    java
    id("io.quarkus") version "2.7.5.Final"
    id("com.github.davidmc24.gradle.plugin.avro") version "1.2.1"

}

repositories {
    mavenCentral()
    mavenLocal()
    maven { url = uri("https://repo.intranet.pags/artifactory/maven-all") }
    maven {
        url = uri("https://repo.quarkus.io/quarkus-release/")
    }
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

extra["avroSerializerVersion"] = "6.2.1"

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-rest-client:2.7.5.Final")
    implementation("io.quarkus:quarkus-resteasy:2.7.5.Final")
    implementation("io.quarkus:quarkus-resteasy-jackson:2.7.5.Final")
    implementation("io.quarkus:quarkus-arc:2.7.5.Final")
    implementation("io.quarkus:quarkus-rest-client-jackson:2.7.5.Final")

    implementation("io.quarkus:quarkus-smallrye-reactive-messaging-kafka:2.7.5.Final")
    implementation("io.quarkus:quarkus-kafka-client:2.7.5.Final")
//    implementation("io.quarkus:quarkus-apicurio-registry-avro:2.7.5.Final")
//    implementation("io.quarkus:quarkus-confluent-registry-avro")
//    implementation("io.confluent:kafka-schema-registry-client:6.2.1")
    implementation("io.confluent:kafka-avro-serializer:6.2.1") {
        exclude("jakarta.ws.rs", "jakarta.ws.rs-api")
    }

//    implementation("io.apicurio:apicurio-registry-serdes-avro-serde:2.4.2.Final")
//    implementation("io.quarkus:quarkus-apicurio-registry-avro:3.0.1.Final")
//    implementation("io.quarkiverse.apicurio:quarkiverse-apicurio-registry-client:0.0.1")
//    implementation ("io.apicurio:apicurio-registry-client:2.1.0.Final")
//    implementation("io.apicurio:apicurio-registry-utils-serde:1.2.2.Final") {
//        exclude("org.jboss.spec.javax.interceptor", "jboss-interceptors-api_1.2_spec")
//    }

    implementation("org.apache.avro:avro:1.10.1")
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

group = "bueno.dev"
version = "2.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}

tasks.quarkusGenerateCodeDev{
    dependsOn(tasks.generateAvroJava)
}

tasks.quarkusGenerateCode{
    dependsOn(tasks.generateAvroJava)
}
tasks.withType<JavaCompile> {
    dependsOn(tasks.generateAvroJava)
    dependsOn(tasks.generateTestAvroJava)
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
    classpath += files("$buildDir/generated-src/avro/v2")
//    classpath += files("src/main/avro")
}

sourceSets {
    main {
        java.srcDirs("$buildDir/generated-src/avro/v2")
//        java.srcDirs("src/main/avro")
    }
}

tasks.withType<com.github.davidmc24.gradle.plugin.avro.GenerateAvroJavaTask> {
    this.setCreateSetters("true")
    this.fieldVisibility.set("PRIVATE")
    this.setOutputDir(file("$buildDir/generated-src/avro/v2"))
//    this.setOutputDir(file("src/main/avro"))
    this.setStringType("String")
    this.setEnableDecimalLogicalType("true")
    this.setSource("src/main/resources/avro/v2")
}
