package br.com.swapi.swapi.data

import org.springframework.data.domain.Pageable

data class Pagination<T>(
        var pageNumber: Int,
        var totalPages: Int,
        var totalPerPage: Int,
        var total: Long,
        var items: List<T>
)