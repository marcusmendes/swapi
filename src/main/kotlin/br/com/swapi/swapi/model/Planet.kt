package br.com.swapi.swapi.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.TextIndexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.util.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 * Classe que define um Documento do MongoDB com o nome de Planet
 */
@Document
class Planet {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var amoutFilms: Int? = null

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var createdAt: Date = Date()

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var updatedAt: Date? = null
}