package org.fountainmc.api

import java.net.InetSocketAddress

interface ServerInfo {

    val name: String

    val version: String

    val motd: String

    val maxPlayers: Int

    val owner: String

    val address: InetSocketAddress

}
