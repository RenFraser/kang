import io.gitlab.arturbosch.detekt.Detekt

plugins {
    kotlin("jvm") version "1.9.0"
    id("io.gitlab.arturbosch.detekt") version "1.23.4"
    id("org.jlleitschuh.gradle.ktlint") version "11.6.0"
    jacoco
    application
    idea
}

group = "io.github"
version = "latest"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

application {
    application {
        mainClass.set("org.kang.MainKt")
    }
}

detekt {
    allRules = false // activate all available (even unstable) rules.
    buildUponDefaultConfig = true // preconfigure defaults
    parallel = true
    config.setFrom(files("$rootDir/detekt.yml"))
    source.setFrom(files(projectDir))
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
    implementation(libs.org.eclipse.lsp4j)
    testImplementation(kotlin("test"))
    testImplementation(libs.junit.jupiter)
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
