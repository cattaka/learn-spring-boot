package net.cattaka.learnspringboot.controller

import net.cattaka.learnspringboot.service.Greeter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(private val greeter: Greeter) {
    @GetMapping("hello")
    fun hello(@RequestParam("name") name : String) : String {
        return greeter.hello(name)
    }
}