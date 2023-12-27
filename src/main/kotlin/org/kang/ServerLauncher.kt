package org.kang

import org.eclipse.lsp4j.launch.LSPLauncher
import org.eclipse.lsp4j.services.LanguageClient
import java.net.ServerSocket

private const val PORT = 64355

class ServerLauncher {
    fun launch() {
        println("Starting Kang...")
        val socket = ServerSocket(PORT).accept()
        val inputStream = socket.getInputStream()
        val outputStream = socket.getOutputStream()

        val server = KotlinLanguageServer()
        val launcher = LSPLauncher.createServerLauncher(server, inputStream, outputStream)

        val client: LanguageClient = launcher.remoteProxy
        println("Connecting...")
        server.connect(client)

        println("Listening...")
        launcher.startListening()
    }
}
