package org.kang

import org.eclipse.lsp4j.launch.LSPLauncher
import org.eclipse.lsp4j.services.LanguageClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.ServerSocket

private const val PORT = 64355

class ServerLauncher {
    fun launch() {
        LOGGER.info("Starting Kang...")
        val socket = ServerSocket(PORT).accept()
        val inputStream = socket.getInputStream()
        val outputStream = socket.getOutputStream()

        val server = KotlinLanguageServer()
        val launcher = LSPLauncher.createServerLauncher(server, inputStream, outputStream)

        val client: LanguageClient = launcher.remoteProxy
        LOGGER.info("Connecting...")
        server.connect(client)

        LOGGER.info("Listening...")
        launcher.startListening()
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(ServerLauncher::class.java)
    }
}
