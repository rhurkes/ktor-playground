val ktorVersion = "1.4.2"
val kotlinVersion = "1.4.10"

fun jackson(subOrg: String, subProject: String) ="com.fasterxml.jackson.$subOrg:jackson-$subProject:2.11.3"

plugins {
    application
    kotlin("jvm") version "1.4.10"
}

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-jackson:1.4.2")
    implementation(jackson("datatype", "datatype-jdk8"))
    implementation(jackson("module", "module-kotlin"))
    implementation("ch.qos.logback:logback-classic:1.2.1")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")

sourceSets["main"].resources.srcDirs("resources")
