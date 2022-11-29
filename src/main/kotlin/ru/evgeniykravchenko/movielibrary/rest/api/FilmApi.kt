package ru.evgeniykravchenko.movielibrary.rest.api

import org.springframework.web.bind.annotation.*
import ru.evgeniykravchenko.movielibrary.rest.dto.FilmDto
import ru.evgeniykravchenko.movielibrary.rest.filter.FilmFilter
import javax.websocket.server.PathParam

@RequestMapping("/movie-api/")
interface FilmApi {
    @GetMapping("movies")
    fun getMoviesWithFilter(
        @RequestBody filmFilter: FilmFilter
    ): List<FilmDto>

    @GetMapping("{id}")
    fun getMovieById(@PathVariable id: Long): FilmDto
}