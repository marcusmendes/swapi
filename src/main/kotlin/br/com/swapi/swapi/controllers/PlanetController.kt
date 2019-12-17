package br.com.swapi.swapi.controllers

import br.com.swapi.swapi.data.Pagination
import br.com.swapi.swapi.data.SuccessResponse
import br.com.swapi.swapi.model.Planet
import br.com.swapi.swapi.service.PlanetService
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/planet")
class PlanetController(private val planetService: PlanetService) {

    @GetMapping("")
    fun index(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false) search: String?
    ): Pagination<Planet> = planetService.listAll(page, search)

    @PostMapping("")
    fun store(@RequestBody @Valid planet: Planet): Planet = planetService.addPlanet(planet)

    @GetMapping("/{idPlanet}")
    fun show(@PathVariable("idPlanet", required = true) idPlanet: String): Optional<Planet> =
        planetService.getPlanet(idPlanet)

    @DeleteMapping("/{idPlanet}")
    fun destroy(@PathVariable("idPlanet", required = true) idPlanet: String): SuccessResponse =
        planetService.removePlanet(idPlanet)
}