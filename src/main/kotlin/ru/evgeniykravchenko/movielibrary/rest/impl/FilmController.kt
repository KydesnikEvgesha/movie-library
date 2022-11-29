package ru.evgeniykravchenko.movielibrary.rest.impl

import org.springframework.web.bind.annotation.RestController
import ru.evgeniykravchenko.movielibrary.dao.service.FilmService
import ru.evgeniykravchenko.movielibrary.rest.api.FilmApi
import ru.evgeniykravchenko.movielibrary.rest.dto.FilmDto
import ru.evgeniykravchenko.movielibrary.rest.filter.FilmFilter

@RestController
class FilmController(
    private val filmService: FilmService
) : FilmApi {
    override fun getMoviesWithFilter(filmFilter: FilmFilter): List<FilmDto> {
        return filmService.getByFilter(filmFilter)
    }

    override fun getMovieById(id: Long): FilmDto = filmService.getById(id)
}