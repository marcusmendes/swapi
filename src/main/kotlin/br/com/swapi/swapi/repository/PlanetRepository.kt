package br.com.swapi.swapi.repository

import br.com.swapi.swapi.model.Planet
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.*

interface PlanetRepository: MongoRepository<Planet, String> {

    /**
     * Busca um planeta pelo nome
     *
     * @param name String
     * @return Optional<Planet>
     */
    @Query(value = "{\$text: { \$search: ?0, \$caseSensitive: false}}")
    fun findByName(name: String): Optional<Planet>
}