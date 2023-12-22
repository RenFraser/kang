import org.eclipse.lsp4j.DidChangeConfigurationParams
import org.eclipse.lsp4j.DidChangeWatchedFilesParams
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.lsp4j.services.LanguageClientAware
import org.eclipse.lsp4j.services.WorkspaceService

class KotlinWorkspaceService : WorkspaceService, LanguageClientAware {
    override fun didChangeConfiguration(p0: DidChangeConfigurationParams?) {
        println("changed configuration")
    }

    override fun didChangeWatchedFiles(p0: DidChangeWatchedFilesParams?) {
        println("changed watched files")
    }

    override fun connect(p0: LanguageClient?) {
        println("connected!")
    }
}
