package br.com.swapi.swapi.exception

import java.lang.Exception

/**
 * Classe que lança exceções geradas pela API
 */
class ApiException(message: String): Exception(message) {}