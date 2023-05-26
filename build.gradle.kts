import io.gitlab.arturbosch.detekt.Detekt

plugins {
    kotlin("jvm") version "1.8.21"
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.3.2"
    jacoco
    application
    idea
}

group = "io.github"
version = "latest"

repositories {
    mavenCentral()
}

detekt {
    allRules = false // activate all available (even unstable) rules.
    buildUponDefaultConfig = true // preconfigure defaults
    parallel = true
    config = files("$rootDir/detekt.yml")
    source = files(projectDir)
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = JavaVersion.VERSION_17.toString()
    exclude("**/build/**")
    reports {
        html.required.set(true)
        md.required.set(true)
    }
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks {
    test {
        useJUnitPlatform()
        finalizedBy(jacocoTestCoverageVerification, jacocoTestReport)
    }

    compileKotlin {
        kotlinOptions.allWarningsAsErrors = true
    }

    jacocoTestReport {
        dependsOn(test)
    }

    jacocoTestCoverageVerification {
        violationRules {
            rule {
                limit {
                    minimum = "0.9".toBigDecimal()
                }
            }
        }
    }
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}
