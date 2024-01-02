package org.kang

import org.eclipse.lsp4j.ClientCapabilities
import org.eclipse.lsp4j.InitializeParams
import org.eclipse.lsp4j.InitializeResult
import org.eclipse.lsp4j.ServerCapabilities
import org.eclipse.lsp4j.TextDocumentSyncKind
import org.eclipse.lsp4j.jsonrpc.messages.Either
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.lsp4j.services.LanguageClientAware
import org.eclipse.lsp4j.services.LanguageServer
import org.eclipse.lsp4j.services.TextDocumentService
import org.eclipse.lsp4j.services.WorkspaceService
import org.jetbrains.dokka.DokkaSourceSetID
import org.jetbrains.dokka.DokkaSourceSetImpl
import org.jetbrains.dokka.analysis.DokkaResolutionFacade
import org.jetbrains.dokka.analysis.KotlinAnalysis
import org.jetbrains.dokka.utilities.DokkaConsoleLogger
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import java.io.File
import java.net.URI
import java.util.concurrent.CompletableFuture
import kotlin.system.exitProcess

class KotlinLanguageServer : LanguageServer, LanguageClientAware {

    private lateinit var client: LanguageClient
    private val workspaceService = KotlinWorkspaceService(this)
    private val textDocumentService = KotlinTextDocumentService(this)
    private lateinit var clientCapabilities: ClientCapabilities
    lateinit var environment: KotlinCoreEnvironment
    lateinit var facade: DokkaResolutionFacade

    override fun initialize(params: InitializeParams?): CompletableFuture<InitializeResult> {
        requireNotNull(params)

        // Copied from: https://medium.com/virtuslab/analyzing-kotlin-sources-just-got-simpler-48aa88e0cf0b
        // We only account for a single workspace directory.
        // If we need to work with multiple directories, change below.
        val roots = setOf(File(URI(params.workspaceFolders.first().uri)))
        val sourceset = DokkaSourceSetImpl(
            displayName = "kang",
            sourceSetID = DokkaSourceSetID("kangScopeID", "kangJVM"),
            sourceRoots = roots
        )
        val kotlinAnalysis = KotlinAnalysis(listOf(sourceset), DokkaConsoleLogger)
        val (environment, facade) = kotlinAnalysis[sourceset]

        this.environment = environment
        this.facade = facade

        val serverCapabilities = ServerCapabilities().apply {
            hoverProvider = Either.forLeft(true)
        }

        val response = InitializeResult(serverCapabilities)

        // Set the document synchronization capabilities to full.
        response.capabilities.setTextDocumentSync(TextDocumentSyncKind.Full)
        this.clientCapabilities = checkNotNull(params.capabilities)

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
