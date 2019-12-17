package br.com.swapi.swapi.data

/**
 * Classe que representa um objeto de erro de validação
 */
data class ValidationError(var field: String, var messages: String)