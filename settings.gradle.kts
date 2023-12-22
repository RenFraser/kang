
rootProject.name = "kang"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("org.eclipse.lsp4j", "org.eclipse.lsp4j:org.eclipse.lsp4j:0.21.1")
            library("junit-jupiter", "org.junit.jupiter:junit-jupiter:5.8.1")
        }
    }
}
