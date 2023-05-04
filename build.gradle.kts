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
}

tasks.test {
    useJUnitPlatform()
}