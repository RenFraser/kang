package org.kang

import org.eclipse.lsp4j.DidChangeTextDocumentParams
import org.eclipse.lsp4j.DidCloseTextDocumentParams
import org.eclipse.lsp4j.DidOpenTextDocumentParams
import org.eclipse.lsp4j.DidSaveTextDocumentParams
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.lsp4j.services.LanguageClientAware
import org.eclipse.lsp4j.services.TextDocumentService
import org.slf4j.LoggerFactory

class KotlinTextDocumentService : TextDocumentService, LanguageClientAware {
    override fun didOpen(p0: DidOpenTextDocumentParams?) {
        LOGGER.info("opened text document")
    }

    override fun didChange(p0: DidChangeTextDocumentParams?) {
        LOGGER.info("changed text document")
    }

    override fun didClose(p0: DidCloseTextDocumentParams?) {
        LOGGER.info("changed closed text document")
    }

    override fun didSave(p0: DidSaveTextDocumentParams?) {
        LOGGER.info("saved text document")
    }

    override fun connect(p0: LanguageClient?) {
        LOGGER.info("connected!")
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(KotlinTextDocumentService::class.java)
    }
}
