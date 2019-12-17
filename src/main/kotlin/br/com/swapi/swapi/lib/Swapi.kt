package br.com.swapi.swapi.lib

import br.com.swapi.swapi.lib.model.SwapiPlanet
import br.com.swapi.swapi.lib.model.SwapiResponse
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.*

/**
 * Classe reponsável por se comunicar com a API https://swapi.co
 * @see <https://swapi.co>
 */
@Service("swapi")
class Swapi {
    private val baseUrl: String = "https://swapi.co/api/"
    private val restTemplate: RestTemplate = RestTemplate()

    /**
     * Recupera o planeta pelo nome
     *
     * @param name String
     * @return Optional<PlanetSwapi>
     */
    fun getPlanetByName(name: String): Optional<SwapiPlanet> {
        val url = baseUrl + "planets/?search=" + name
        val response = restTemplate.getForObject(url, SwapiResponse::class.java)!!

        if (response.results.isEmpty()) {
            return Optional.ofNullable(null)
        }

        return Optional.ofNullable(response.results[0])
    }
}