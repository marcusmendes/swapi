package br.com.swapi.swapi.service

import br.com.swapi.swapi.model.Planet
import org.springframework.stereotype.Service

@Service("planetService")
class PlanetService: PlanetServiceContract {
    override fun listAll(keyword: String?): List<Planet> {
        return listOf(Planet("Bu", "Tu", "Pu", 1, "abcde"));
    }

    override fun addPlanet(planet: Planet): Planet {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPlanet(idPlanet: Int): Planet {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removePlanet(idPlanet: Int): Map<String, String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}