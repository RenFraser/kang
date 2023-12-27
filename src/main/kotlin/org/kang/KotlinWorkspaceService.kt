package org.kang

import org.eclipse.lsp4j.DidChangeConfigurationParams
import org.eclipse.lsp4j.DidChangeWatchedFilesParams
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.lsp4j.services.LanguageClientAware
import org.eclipse.lsp4j.services.WorkspaceService
import org.slf4j.LoggerFactory

class KotlinWorkspaceService : WorkspaceService, LanguageClientAware {
    override fun didChangeConfiguration(p0: DidChangeConfigurationParams?) {
        LOGGER.info("changed configuration")
    }

    override fun didChangeWatchedFiles(p0: DidChangeWatchedFilesParams?) {
        LOGGER.info("changed watched files")
    }

    override fun connect(p0: LanguageClient?) {
        LOGGER.info("connected!")
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(KotlinWorkspaceService::class.java)
    }
}
