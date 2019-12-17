package br.com.swapi.swapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class SwapiApplication

fun main(args: Array<String>) {
	runApplication<SwapiApplication>(*args)
}
