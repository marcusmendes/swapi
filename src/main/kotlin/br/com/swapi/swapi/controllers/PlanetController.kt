package br.com.swapi.swapi.controllers

import br.com.swapi.swapi.model.Planet
import br.com.swapi.swapi.service.PlanetService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/planet")
class PlanetController(val planetService: PlanetService) {

    @GetMapping("")
    fun index(@RequestParam(required = false) search: String?): List<Planet> {
        return planetService.listAll(search);
    }
}