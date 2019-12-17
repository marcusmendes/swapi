package br.com.swapi.swapi.service

import br.com.swapi.swapi.data.Pagination
import br.com.swapi.swapi.data.SuccessResponse
import br.com.swapi.swapi.model.Planet
import java.util.*

interface PlanetService {
    /**
     * Lista todos os planetas
     *
     * @param page Int
     * @param keyword String
     * @return Pagination<Planet>
     */
    fun listAll(page: Int, keyword: String?): Pagination<Planet>

    /**
     * Adiciona um novo planeta
     *
     * @param planet Planet
     * @return Planet
     */
    fun addPlanet(planet: Planet): Planet

    /**
     * Recupera os dados de um planeta pelo seu Id
     *
     * @param idPlanet String
     * @return Optional<Planet>
     */
    fun getPlanet(idPlanet: String): Optional<Planet>

    /**
     * Remove um planeta pelo seu Id
     *
     * @param idPlanet String
     * @return SuccessResponse
     */
    fun removePlanet(idPlanet: String): SuccessResponse
}