package br.com.swapi.swapi.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.TextIndexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.util.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Document
class Planet {
    @Id
    var id: String? = null

    @NotNull(message = "O nome do planeta é obrigatório!")
    @NotEmpty(message = "Informe um nome para o planeta.")
    @TextIndexed
    lateinit var name: String

    @NotNull(message = "O clima do planeta é obrigatório!")
    @NotEmpty(message = "Informe o clima do planeta.")
    lateinit var climate: String

    @NotNull(message = "O terreno do planeta é obrigatório!")
    @NotEmpty(message = "Informe o terreno do planeta.")
    lateinit var terrain: String

    var amoutFilms: Int? = null

    var createdAt: Date = Date()

    var updatedAt: Date? = null
}