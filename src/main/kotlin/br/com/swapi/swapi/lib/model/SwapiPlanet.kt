package br.com.swapi.swapi.lib.model

/**
 * Classe representa um planeta da API swapi.co
 * @see <https://swapi.co/api/planets/>
 */
class SwapiPlanet {
    var name: String? = null
    var climate: String? = null
    var terrain: String? = null
    lateinit var films: List<String>
}