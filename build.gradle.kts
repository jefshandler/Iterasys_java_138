plugins {
    id("java")
}

group = "br.com.iterasys"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    
    // https://mvnrepository.com/artifact/org.testng/testng
    testImplementation("org.testng:testng:7.7.1")
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation("org.seleniumhq.selenium:selenium-java:4.10.0")
    implementation("io.github.bonigarcia:webdrivermanager:5.4.0")
    // https://mvnrepository.com/artifact/io.rest-assured/rest-assured
    testImplementation("io.rest-assured:rest-assured:5.3.0")
    implementation ("ch.qos.logback:logback-classic:1.4.7")
    implementation ("org.slf4j:slf4j-api:2.0.5")


}

tasks.test {
    useTestNG()
}