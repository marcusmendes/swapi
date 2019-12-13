package br.com.swapi.swapi.model

import org.springframework.data.annotation.Id

class Planet(
        var name: String,
        var climate: String,
        var terrain: String,
        var amoutFilms: Int? = null,
        @Id var id: String? = null
)