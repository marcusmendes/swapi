package br.com.swapi.swapi.controllers

import br.com.swapi.swapi.data.Pagination
import br.com.swapi.swapi.model.Planet
import br.com.swapi.swapi.service.PlanetService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/planet")
class PlanetController(val planetService: PlanetService) {

    @GetMapping("")
    fun index(
            @RequestParam(required = false, defaultValue = "0") page: Int,
            @RequestParam(required = false) search: String?
    ): Pagination<Planet> {
        return planetService.listAll(page, search)
    }

    @PostMapping("")
    fun store(@RequestBody @Valid planet: Planet): Planet {
        return planetService.addPlanet(planet)
    }
}