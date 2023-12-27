package org.kang

import java.util.concurrent.CompletableFuture
import org.eclipse.lsp4j.CreateFilesParams
import org.eclipse.lsp4j.DeleteFilesParams
import org.eclipse.lsp4j.DidChangeConfigurationParams
import org.eclipse.lsp4j.DidChangeWatchedFilesParams
import org.eclipse.lsp4j.DidChangeWorkspaceFoldersParams
import org.eclipse.lsp4j.ExecuteCommandParams
import org.eclipse.lsp4j.RenameFilesParams
import org.eclipse.lsp4j.SymbolInformation
import org.eclipse.lsp4j.WorkspaceDiagnosticParams
import org.eclipse.lsp4j.WorkspaceDiagnosticReport
import org.eclipse.lsp4j.WorkspaceEdit
import org.eclipse.lsp4j.WorkspaceSymbol
import org.eclipse.lsp4j.WorkspaceSymbolParams
import org.eclipse.lsp4j.jsonrpc.messages.Either
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.lsp4j.services.LanguageClientAware
import org.eclipse.lsp4j.services.WorkspaceService
import org.slf4j.LoggerFactory

class KotlinWorkspaceService : WorkspaceService, LanguageClientAware {
    override fun executeCommand(params: ExecuteCommandParams?): CompletableFuture<Any> {
        return super.executeCommand(params)
    }

    override fun symbol(params: WorkspaceSymbolParams?): CompletableFuture<Either<MutableList<out SymbolInformation>, MutableList<out WorkspaceSymbol>>> {
        return super.symbol(params)
    }

    override fun resolveWorkspaceSymbol(workspaceSymbol: WorkspaceSymbol?): CompletableFuture<WorkspaceSymbol> {
        return super.resolveWorkspaceSymbol(workspaceSymbol)
    }

    override fun didChangeConfiguration(p0: DidChangeConfigurationParams?) {
        LOGGER.info("changed configuration")
    }

    override fun didChangeWatchedFiles(p0: DidChangeWatchedFilesParams?) {
        LOGGER.info("changed watched files")
    }

    override fun didChangeWorkspaceFolders(params: DidChangeWorkspaceFoldersParams?) {
        super.didChangeWorkspaceFolders(params)
    }

    override fun willCreateFiles(params: CreateFilesParams?): CompletableFuture<WorkspaceEdit> {
        return super.willCreateFiles(params)
    }

    override fun didCreateFiles(params: CreateFilesParams?) {
        super.didCreateFiles(params)
    }

    override fun willRenameFiles(params: RenameFilesParams?): CompletableFuture<WorkspaceEdit> {
        return super.willRenameFiles(params)
    }

    override fun didRenameFiles(params: RenameFilesParams?) {
        super.didRenameFiles(params)
    }

    override fun willDeleteFiles(params: DeleteFilesParams?): CompletableFuture<WorkspaceEdit> {
        return super.willDeleteFiles(params)
    }

    override fun didDeleteFiles(params: DeleteFilesParams?) {
        super.didDeleteFiles(params)
    }

    override fun diagnostic(params: WorkspaceDiagnosticParams?): CompletableFuture<WorkspaceDiagnosticReport> {
        return super.diagnostic(params)
    }

    override fun connect(p0: LanguageClient?) {
        LOGGER.info("connected!")
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(KotlinWorkspaceService::class.java)
    }
}
