import org.eclipse.lsp4j.DidChangeTextDocumentParams
import org.eclipse.lsp4j.DidCloseTextDocumentParams
import org.eclipse.lsp4j.DidOpenTextDocumentParams
import org.eclipse.lsp4j.DidSaveTextDocumentParams
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.lsp4j.services.LanguageClientAware
import org.eclipse.lsp4j.services.TextDocumentService

class KotlinTextDocumentService : TextDocumentService, LanguageClientAware {
    override fun didOpen(p0: DidOpenTextDocumentParams?) {
        println("opened text document")
    }

    override fun didChange(p0: DidChangeTextDocumentParams?) {
        println("changed text document")
    }

    override fun didClose(p0: DidCloseTextDocumentParams?) {
        println("changed closed text document")
    }

    override fun didSave(p0: DidSaveTextDocumentParams?) {
        println("saved text document")
    }

    override fun connect(p0: LanguageClient?) {
        println("connected!")
    }
}
