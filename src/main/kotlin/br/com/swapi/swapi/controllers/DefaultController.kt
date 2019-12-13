package br.com.swapi.swapi.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DefaultController {
    @GetMapping("/")
    fun index(): Map<String, String> {
        return mapOf("message" to "API Star Wars")
    }
}