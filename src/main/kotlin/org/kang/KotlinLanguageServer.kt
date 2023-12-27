package org.kang

import org.eclipse.lsp4j.ClientCapabilities
import org.eclipse.lsp4j.CompletionOptions
import org.eclipse.lsp4j.InitializeParams
import org.eclipse.lsp4j.InitializeResult
import org.eclipse.lsp4j.ServerCapabilities
import org.eclipse.lsp4j.TextDocumentSyncKind
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.lsp4j.services.LanguageClientAware
import org.eclipse.lsp4j.services.LanguageServer
import org.eclipse.lsp4j.services.TextDocumentService
import org.eclipse.lsp4j.services.WorkspaceService
import java.util.concurrent.CompletableFuture
import kotlin.system.exitProcess

class KotlinLanguageServer : LanguageServer, LanguageClientAware {

    private lateinit var client: LanguageClient
    private val workspaceService = KotlinWorkspaceService()
    private val textDocumentService = KotlinTextDocumentService()
    private lateinit var clientCapabilities: ClientCapabilities

    override fun initialize(params: InitializeParams?): CompletableFuture<InitializeResult> {
        requireNotNull(params)

        val response = InitializeResult(ServerCapabilities())

        // Set the document synchronization capabilities to full.
        response.capabilities.setTextDocumentSync(TextDocumentSyncKind.Full)
        this.clientCapabilities = checkNotNull(params.capabilities)

        // Check if dynamic registration of completion capability is allowed by the client.
        // If so we don't register the capability.
        // Else, we register the completion capability.
        val isDynamicCompletionRegistration = params.capabilities
            ?.textDocument
            ?.completion
            ?.dynamicRegistration
            ?: false

        if (!isDynamicCompletionRegistration) {
            response.capabilities.completionProvider = CompletionOptions()
        }
        return CompletableFuture.supplyAsync { response }
    }

    override fun shutdown(): CompletableFuture<Any> {
        return CompletableFuture.supplyAsync(null)
    }

    override fun exit() {
        exitProcess(0)
    }

    override fun getTextDocumentService(): TextDocumentService {
        return this.textDocumentService
    }

    override fun getWorkspaceService(): WorkspaceService {
        return this.workspaceService
    }

    override fun connect(client: LanguageClient?) {
        this.client = checkNotNull(client)
        this.workspaceService.connect(this.client)
        this.textDocumentService.connect(this.client)
    }
}
