package br.com.swapi.swapi.data

/**
 * Classe que representa um objeto de paginação
 */
data class Pagination<T>(
        var pageNumber: Int,
        var totalPages: Int,
        var totalPerPage: Int,
        var total: Long,
        var items: List<T>
)