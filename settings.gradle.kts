
rootProject.name = "kang"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("org.eclipse.lsp4j", "org.eclipse.lsp4j:org.eclipse.lsp4j:0.21.1")
            library("junit-jupiter", "org.junit.jupiter:junit-jupiter:5.10.1")
            library("kotlinx-coroutines-core", "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC2")
            library("kotlinx-coroutines-test", "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0-RC2")
            library("slf4j-api", "org.slf4j:slf4j-api:2.0.10")
            library("log4j-slf4j2-impl", "org.apache.logging.log4j:log4j-slf4j2-impl:2.22.1")
        }
    }
}
