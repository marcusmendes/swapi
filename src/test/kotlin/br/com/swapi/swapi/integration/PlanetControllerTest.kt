package br.com.swapi.swapi.integration

import br.com.swapi.swapi.lib.Swapi
import br.com.swapi.swapi.lib.model.SwapiPlanet
import br.com.swapi.swapi.model.Planet
import br.com.swapi.swapi.repository.PlanetRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest
class PlanetControllerTest {

    @MockkBean
    private lateinit var planetRepository: PlanetRepository

    @MockkBean
    private lateinit var swapi: Swapi

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup(webApplicationContext: WebApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    @Test
    fun `listar todos os planetas com paginacao`() {
        val planet = Planet().apply {
            this.id = "absadfqwwrewqr"
            this.name = "Dagobah"
            this.climate = "murky"
            this.terrain = "terrain"
            this.amoutFilms = 1
            this.createdAt = Date()
            this.updatedAt = Date()
        }

        val pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt")
        val pageImpl = PageImpl(mutableListOf(planet))

        every { planetRepository.findAll(pageable) } returns pageImpl

        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/planet").accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.pageNumber").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.totalPerPage").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].id").value("absadfqwwrewqr"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].name").value("Dagobah"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].climate").value("murky"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].terrain").value("terrain"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].amoutFilms").value(1))
    }

    @Test
    fun `buscar um planeta pelo nome e retornar uma lista paginada`() {
        val planet = Planet().apply {
            this.id = "sdfgxzcvsadfsa"
            this.name = "Zurah"
            this.climate = "murky"
            this.terrain = "terrain"
            this.amoutFilms = 2
            this.createdAt = Date()
            this.updatedAt = Date()
        }

        val pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt")
        val pageImpl = PageImpl(mutableListOf(planet))
        val keyword = "zurah"

        every { planetRepository.findAllByName(keyword, pageable) } returns pageImpl

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .get("/api/planet")
                    .param("search", keyword)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.pageNumber").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.totalPerPage").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].id").value("sdfgxzcvsadfsa"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].name").value("Zurah"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].climate").value("murky"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].terrain").value("terrain"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].amoutFilms").value(2))
    }

    @Test
    fun `adicionar um planeta e retornar o planeta adicionado`() {
        val planet = "{\"name\": \"Zurah\", \"climate\": \"murky\", \"terrain\": \"terrain\"}";

        val planetName = "Zurah"

        val swapiPlanet = SwapiPlanet().apply {
            this.name = planetName
            this.terrain = "murky"
            this.climate = "terrain"
            this.films = listOf("film1")
        }

        val planetAdded = Planet().apply {
            this.id = "sdfgxzcvsadfsa"
            this.name = planetName
            this.climate = "murky"
            this.terrain = "terrain"
            this.amoutFilms = swapiPlanet.films.size
            this.createdAt = Date()
        }

        every { planetRepository.findOneByName(planetName) } returns Optional.ofNullable(null)
        every { planetRepository.save(allAny<Planet>()) } returns planetAdded
        every { swapi.getPlanetByName(planetName) } returns Optional.of(swapiPlanet)

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/api/planet")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(planet)
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(planetAdded.id!!))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(planetAdded.name))
            .andExpect(MockMvcResultMatchers.jsonPath("$.climate").value(planetAdded.climate))
            .andExpect(MockMvcResultMatchers.jsonPath("$.terrain").value(planetAdded.terrain))
            .andExpect(MockMvcResultMatchers.jsonPath("$.amoutFilms").value(planetAdded.amoutFilms!!))
    }

    @Test
    fun `deve retornar erro ao adicionar um planeta que ja existe`() {
        val planet = "{\"name\": \"Zurah\", \"climate\": \"murky\", \"terrain\": \"terrain\"}";

        val planetName = "Zurah"

        val planetExisted = Planet().apply {
            this.id = "sdfgxzcvsadfsa"
            this.name = planetName
            this.climate = "murky"
            this.terrain = "terrain"
            this.amoutFilms = 1
            this.createdAt = Date()
        }

        every { planetRepository.findOneByName(planetName) } returns Optional.ofNullable(planetExisted)

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/api/planet")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(planet)
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Esse planeta já foi adicionado!"))
    }
}