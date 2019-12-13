package br.com.swapi.swapi.service

import br.com.swapi.swapi.model.Planet

interface PlanetServiceContract {
    /**
     * Lista todos os planetas
     *
     * @param keyword String
     * @return List<Planet>
     */
    fun listAll(keyword: String?): List<Planet>

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