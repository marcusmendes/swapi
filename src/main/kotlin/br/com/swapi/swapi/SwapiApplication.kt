package br.com.swapi.swapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
// @ComponentScan(value = ["br.com.swapi.swapi.service", "br.com.swapi.swapi.lib", "br.com.swapi.swapi.repository"])
class SwapiApplication

fun main(args: Array<String>) {
	runApplication<SwapiApplication>(*args)
}
