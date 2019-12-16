package br.com.swapi.swapi.service

import br.com.swapi.swapi.data.Pagination
import br.com.swapi.swapi.model.Planet

interface PlanetServiceContract {
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
     * @param idPlanet Int
     * @return Planet
     */
    fun getPlanet(idPlanet: Int): Planet

    /**
     * Remove um planeta pelo seu Id
     *
     * @param idPlanet Int
     * @return Map<String, String>
     */
    fun removePlanet(idPlanet: Int): Map<String, String>
}