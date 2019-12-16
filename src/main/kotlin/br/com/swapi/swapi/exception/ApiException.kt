package br.com.swapi.swapi.exception

import java.lang.Exception

class ApiException(message: String): Exception(message) {}