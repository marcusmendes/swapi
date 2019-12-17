package br.com.swapi.swapi.controllers

import br.com.swapi.swapi.data.Pagination
import br.com.swapi.swapi.data.SuccessResponse
import br.com.swapi.swapi.model.Planet
import br.com.swapi.swapi.service.PlanetService
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

/**
 * Classe que gerencia as requisições para a rota /api/planet
 */
@RestController
@RequestMapping("/api/planet")
class PlanetController(private val planetService: PlanetService) {

    /**
     * Rota que lista todos os planetas com paginação e também realiza busca pelo nome do planeta
     *
     * @param page Int A página atual da paginação (Opcional)
     * @param search String? O nome do planeta a ser pesquisado (Opcional)
     * @return Pagination<Planet>
     */
    @GetMapping("")
    fun index(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false) search: String?
    ): Pagination<Planet> = planetService.listAll(page, search)

    /**
     * Rota que adiciona um planeta
     *
     * @param planet Planet Os dados do planeta para serem adicionado
     * @return Planet
     */
    @PostMapping("")
    fun store(@RequestBody @Valid planet: Planet): Planet = planetService.addPlanet(planet)

    /**
     * Rota que retorna os dados de um planeta pelo seu Id
     *
     * @param idPlanet String O Id do planeta a ser recuperado
     * @return Optional<Planet>
     */
    @GetMapping("/{idPlanet}")
    fun show(@PathVariable("idPlanet", required = true) idPlanet: String): Optional<Planet> =
        planetService.getPlanet(idPlanet)

    /**
     * Rota que remove um planeta pelo o seu Id
     *
     * @param idPlanet String O Id do planeta a ser removido
     * @return SuccessResponse
     */
    @DeleteMapping("/{idPlanet}")
    fun destroy(@PathVariable("idPlanet", required = true) idPlanet: String): SuccessResponse =
        planetService.removePlanet(idPlanet)
}