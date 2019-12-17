package br.com.swapi.swapi.configuration

import com.google.common.base.Predicate
import com.google.common.base.Predicates
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    /**
     * Configuração do Swagger
     *
     * @return Docket
     */
    @Bean
    open fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(paths())
        .build()
        .apiInfo(apiInfo())

    /**
     * Gera a documentação da api que corresponda ao caminho especificado
     *
     * @return Predicate<String>
     */
    private fun paths(): Predicate<String> = Predicates.or(PathSelectors.regex("/api/planet.*"))

    /**
     * Gera as informações básica da api
     *
     * @return ApiInfo
     */
    private fun apiInfo(): ApiInfo =
        ApiInfo(
            "API Star Wars",
            "API para recuperar as informações de cada planeta do universo Star Wars",
            "1.0.0",
            "",
            Contact("Marcus", "", "mendesxx.ti@hotmail.com"),
            "",
            "",
            mutableListOf()
        )
}