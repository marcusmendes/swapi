package br.com.swapi.swapi.repository

import br.com.swapi.swapi.model.Planet
import org.springframework.data.mongodb.repository.MongoRepository

interface PlanetRepository: MongoRepository<Planet, String> {}