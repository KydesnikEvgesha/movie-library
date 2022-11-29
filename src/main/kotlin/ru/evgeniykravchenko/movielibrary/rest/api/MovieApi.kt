package ru.evgeniykravchenko.movielibrary.rest.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.evgeniykravchenko.movielibrary.rest.dto.MovieDto
import ru.evgeniykravchenko.movielibrary.rest.error.ErrorMessage
import ru.evgeniykravchenko.movielibrary.rest.filter.MovieFilter

@RequestMapping("/movie-api/")
@Schema(description = "Контроллер для работы с кинолентами", name = "Контроллер кинолент")
interface MovieApi {
    @GetMapping("movies")
    @Operation(
        summary = "Получить коллекцию кинолент из библиотеки на основе фильтра",
        description = "Возвращает 200, если данные найдены",
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", content = [Content(mediaType = "application/json", schema = Schema(implementation = MovieDto::class))], description = "Успешное выполнение операции"),
            ApiResponse(responseCode = "500", content = [Content()], description = "Ошибка сервера")
        ]
    )
    fun getMoviesWithFilter(
        @RequestBody filter: MovieFilter
    ): List<MovieDto>

    @GetMapping("{id}")
    @Operation(summary = "Получить киноленту из библиотеки по идентификатору", description = "Возвращает 200, если данные найдены")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Успешное выполнение операции", content = [Content(mediaType = "application/json", schema = Schema(implementation = MovieDto::class))]),
            ApiResponse(responseCode = "404", description = "Данные не найдены", content = [Content()]),
            ApiResponse(responseCode = "500", description = "Ошибка сервера", content = [Content()])
        ]
    )
    fun getMovieById(@PathVariable id: Long): MovieDto

    @PostMapping
    @Operation(summary = "Добавить киноленты в библиотеку", description = "Возвращает 200, если данные найдены")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Успешное выполнение операции", content = [Content(mediaType = "application/json", schema = Schema(implementation = MovieDto::class))]),
            ApiResponse(responseCode = "500", description = "Ошибка сервера", content = [Content()])
        ]
    )
    fun insertMovies(@RequestBody movies: List<MovieDto>)
}