package br.com.swapi.swapi.service

import br.com.swapi.swapi.exception.ApiException
import br.com.swapi.swapi.lib.Swapi
import br.com.swapi.swapi.model.Planet
import br.com.swapi.swapi.repository.PlanetRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service("planetService")
class PlanetService(val planetRepository: PlanetRepository, val swapi: Swapi): PlanetServiceContract {

    /**
     * Lista todos os planetas e também busca pelo nome
     *
     * @param keyword String
     * @return List<Planet>
     */
    override fun listAll(keyword: String?): List<Planet> {
        val planets = planetRepository.findAll(Sort.by(Sort.Order.desc("createdAt")))


        return planets
    }

    /**
     * Adiciona um novo planeta
     *
     * @param planet Planet
     * @return Planet
     */
    override fun addPlanet(planet: Planet): Planet {
        val planetExists = planetRepository.findByName(planet.name)

        if (planetExists.isPresent) {
            throw ApiException("Esse planeta já foi adicionado!")
        }

        val swapiPlanet = swapi.getPlanetByName(planet.name)

        if (swapiPlanet.isPresent) {
            planet.amoutFilms = swapiPlanet.get().films.size
        }

        planetRepository.save(planet)

        return planet
    }

    /**
     * Recupera os dados de um planeta pelo seu Id
     *
     * @param idPlanet Int
     * @return Planet
     */
    override fun getPlanet(idPlanet: Int): Planet {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Remove um planeta pelo seu Id
     *
     * @param idPlanet Int
     * @return Map<String, String>
     */
    override fun removePlanet(idPlanet: Int): Map<String, String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}