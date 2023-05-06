plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.mysql:mysql-connector-j:8.0.33")
    implementation("org.hibernate.orm:hibernate-core:6.2.2.Final")

//    compileOnly("org.projectlombok:lombok:1.18.26")
//    annotationProcessor("org.projectlombok:lombok:1.18.26")

    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("org.slf4j:slf4j-simple:2.0.7")

    implementation("com.github.javafaker:javafaker:1.0.2")
}

tasks.test {
    useJUnitPlatform()
}
