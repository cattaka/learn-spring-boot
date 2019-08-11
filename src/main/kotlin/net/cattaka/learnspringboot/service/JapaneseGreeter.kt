package net.cattaka.learnspringboot.service

import org.springframework.stereotype.Component

interface Greeter {
    fun hello(name : String): String
}

@Component
class JapaneseGreeter : Greeter {
    override fun hello(name : String): String = "こんにちは、$name！"
}
