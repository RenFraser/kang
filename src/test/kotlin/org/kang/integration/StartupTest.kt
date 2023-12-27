package org.kang.integration

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.eclipse.lsp4j.launch.LSPLauncher
import org.junit.jupiter.api.Test
import org.kang.KotlinLanguageClient
import org.kang.ServerLauncher
import java.net.ConnectException
import java.net.Socket

class StartupTest {
    @Test
    fun `kang should be able to serve a connection`() = runTest {
        runBlocking {
            val first = async(Dispatchers.IO) {
                val serverLauncher = ServerLauncher()
                serverLauncher.launch()
            }

            val second = async(Dispatchers.IO) {
                val client = KotlinLanguageClient()
                var attempts = 0

                while (true) {
                    try {
                        println("attempting to connect")
                        val socket = Socket("localhost", 64355)
                        LSPLauncher.createClientLauncher(client, socket.getInputStream(), socket.getOutputStream())

                        if (socket.isConnected) {
                            return@async
                        }
                    } catch (e: ConnectException) {
                        println("caught connection exception")
                        if (attempts >= 5) {
                            throw e
                        }

                        delay(1000)
                        attempts++
                    }
                }
            }

            val tasks = listOf(first, second)
            tasks.joinAll()
        }
    }
}
