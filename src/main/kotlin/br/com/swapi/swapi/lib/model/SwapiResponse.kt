package br.com.swapi.swapi.lib.model

/**
 * Classe representa a resposta da API
 * @see <https://swapi.co>
 */
class SwapiResponse {
    var count: Int? = null
    var next: String? = null
    var previous: String? = null
    lateinit var results: List<SwapiPlanet>
}