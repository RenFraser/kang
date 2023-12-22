package org.kang

import org.eclipse.lsp4j.MessageActionItem
import org.eclipse.lsp4j.MessageParams
import org.eclipse.lsp4j.PublishDiagnosticsParams
import org.eclipse.lsp4j.ShowMessageRequestParams
import org.eclipse.lsp4j.services.LanguageClient
import java.util.concurrent.CompletableFuture

class KotlinLanguageClient : LanguageClient {
    override fun telemetryEvent(p0: Any?) {
        return
    }

    override fun publishDiagnostics(p0: PublishDiagnosticsParams?) {
        return
    }

    override fun showMessage(p0: MessageParams?) {
        return
    }

    override fun showMessageRequest(p0: ShowMessageRequestParams?): CompletableFuture<MessageActionItem> {
        return CompletableFuture<MessageActionItem>().completeAsync { null }
    }

    override fun logMessage(p0: MessageParams?) {
        return
    }
}
