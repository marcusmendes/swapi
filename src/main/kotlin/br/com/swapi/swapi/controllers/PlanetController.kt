package br.com.swapi.swapi.controllers

import br.com.swapi.swapi.model.Planet
import br.com.swapi.swapi.service.PlanetService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/planet")
class PlanetController(val planetService: PlanetService) {

    @GetMapping("")
    fun index(@RequestParam(required = false) search: String?): List<Planet> {
        return planetService.listAll(search)
    }

    @PostMapping("")
    fun store(@RequestBody @Valid planet: Planet): Planet {
        return planetService.addPlanet(planet)
    }
}