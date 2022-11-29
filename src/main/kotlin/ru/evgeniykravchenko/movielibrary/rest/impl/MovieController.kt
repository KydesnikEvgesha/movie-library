package ru.evgeniykravchenko.movielibrary.rest.impl

import org.springframework.web.bind.annotation.RestController
import ru.evgeniykravchenko.movielibrary.dao.service.MovieService
import ru.evgeniykravchenko.movielibrary.rest.api.MovieApi
import ru.evgeniykravchenko.movielibrary.rest.dto.MovieDto
import ru.evgeniykravchenko.movielibrary.rest.filter.MovieFilter

@RestController
class MovieController(
    private val movieService: MovieService
) : MovieApi {
    override fun getMoviesWithFilter(movieFilter: MovieFilter): List<MovieDto> {
        return movieService.getByFilter(movieFilter)
    }

    override fun getMovieById(id: Long): MovieDto = movieService.getById(id)

    override fun insertMovies(movies: List<MovieDto>) = movieService.insertFilmsIntoLibrary(movies)
}