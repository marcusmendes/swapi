package br.com.swapi.swapi.service

import br.com.swapi.swapi.data.Pagination
import br.com.swapi.swapi.exception.ApiException
import br.com.swapi.swapi.lib.Swapi
import br.com.swapi.swapi.model.Planet
import br.com.swapi.swapi.repository.PlanetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service("planetService")
class PlanetServiceImpl: PlanetService {

    @Autowired
    private lateinit var planetRepository: PlanetRepository

    @Autowired
    private lateinit var swapi: Swapi

    /**
     * Lista todos os planetas e também busca pelo nome
     *
     * @param keyword String
     * @return Pagination<Planet>
     */
    override fun listAll(page: Int, keyword: String?): Pagination<Planet> {
        val pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "createdAt")

        return if (keyword.isNullOrEmpty()) {
            val paginated = planetRepository.findAll(pageable)
            Pagination(paginated.number, paginated.totalPages, paginated.size, paginated.totalElements, paginated.content)
        } else {
            val paginated = planetRepository.findAllByName(keyword, pageable)
            Pagination(paginated.number, paginated.totalPages, paginated.size, paginated.totalElements, paginated.content)
        }
    }

    /**
     * Adiciona um novo planeta
     *
     * @param planet Planet
     * @return Planet
     */
    override fun addPlanet(planet: Planet): Planet {
        val planetExists = planetRepository.findOneByName(planet.name)

        if (planetExists.isPresent) {
            throw ApiException("Esse planeta já foi adicionado!")
        }

        val swapiPlanet = swapi.getPlanetByName(planet.name)

        if (swapiPlanet.isPresent) {
            planet.amoutFilms = swapiPlanet.get().films.size
        }

        return planetRepository.save(planet)
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