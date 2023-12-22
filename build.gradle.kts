import io.gitlab.arturbosch.detekt.Detekt

plugins {
    kotlin("jvm") version "1.9.0"
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
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
    jvmToolchain(11)
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
    implementation("org.eclipse.lsp4j:org.eclipse.lsp4j:0.21.1")
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
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