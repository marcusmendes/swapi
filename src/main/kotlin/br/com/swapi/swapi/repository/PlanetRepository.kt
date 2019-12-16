package br.com.swapi.swapi.repository

import br.com.swapi.swapi.model.Planet
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlanetRepository: MongoRepository<Planet, String> {

    /**
     * Busca um planeta pelo nome
     *
     * @param name String
     * @return Optional<Planet>
     */
    @Query(value = "{'\$text': { '\$search': ?0, '\$caseSensitive': false}}")
    fun findOneByName(name: String): Optional<Planet>

    /**
     * Lista todos os planetas conforme o nome pesquisado
     *
     * @param name String
     * @return List<Planet>
     */
    @Query(value = "{'\$text': { '\$search': ?0, '\$caseSensitive': false}}")
    fun findAllByName(name: String, pageable: Pageable): Page<Planet>
}