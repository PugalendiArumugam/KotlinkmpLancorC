package com.nexus.appartmentlancorc

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform