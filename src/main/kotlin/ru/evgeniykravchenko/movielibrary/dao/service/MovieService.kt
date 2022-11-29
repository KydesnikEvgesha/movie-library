package ru.evgeniykravchenko.movielibrary.dao.service

import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.evgeniykravchenko.movielibrary.common.annotation.LogExecution
import ru.evgeniykravchenko.movielibrary.dao.repository.MovieRepository
import ru.evgeniykravchenko.movielibrary.domain.Movie
import ru.evgeniykravchenko.movielibrary.domain.MovieType
import ru.evgeniykravchenko.movielibrary.rest.dto.MovieDto
import ru.evgeniykravchenko.movielibrary.rest.filter.MovieFilter
import java.time.LocalDate

/**
 * Сервис с бизнес логикой библиотеки фильмов
 *
 * @property movieRepository [MovieRepository]
 * @property queryFactory [SpringDataQueryFactory] надстройка над Criteria API
 */
@Service
class MovieService(
    private val movieRepository: MovieRepository,
    private val queryFactory: SpringDataQueryFactory,
) {
    /**
     * Поиск фильмов по фильтру [MovieFilter]
     *
     * @param movieFilter фильтр
     * @return коллекцию значений [MovieDto]
     */
    fun getByFilter(movieFilter: MovieFilter): List<MovieDto> {
        val nameFilter = movieFilter.movieQuery.firstOrNull { it.fieldName == "name" }
        val typeFilter = movieFilter.movieQuery.firstOrNull { it.fieldName == "type" }
        val yearFilter = movieFilter.movieQuery.firstOrNull { it.fieldName == "year" }
        return queryFactory.listQuery<Movie> {
            selectDistinct(entity(Movie::class))
            from(entity(Movie::class))
            where(
                and(
                    nameFilter?.run { (col(Movie::name).like("%${nameFilter.value}%")) },
                    typeFilter?.run { col(Movie::type).equal(MovieType.valueOf(typeFilter.value)) },
                    yearFilter?.run {
                        col(Movie::releaseDate).between(
                            LocalDate.of(yearFilter.value.toInt(), 1, 1),
                            LocalDate.of(yearFilter.value.toInt(), 12, 31)
                        )
                    }
                )
            )
            offset(movieFilter.offset)
            limit(movieFilter.limit)
        }.map { it.mapToDto() }
    }

    /**
     * Поиска киноленты по идентификатору
     *
     * @param id идентификатор киноленты
     * @return объект [MovieDto]
     */
    @LogExecution
    fun getById(id: Long): MovieDto {
        val film = movieRepository.findByIdOrNull(id) ?: throw NoSuchElementException("Not found movie with id $id")
        return film.mapToDto()
    }

    /**
     * Вставка кинолент в библиотеку
     *
     * @param films коллекция значений [MovieDto]
     */
    fun insertFilmsIntoLibrary(films: List<MovieDto>) {
        movieRepository.saveAll(
            films.map {
                Movie(
                    name = it.name,
                    description = it.description,
                    type = MovieType.valueOf(it.type),
                    releaseDate = it.releaseDate
                )
            }
        )
    }
}