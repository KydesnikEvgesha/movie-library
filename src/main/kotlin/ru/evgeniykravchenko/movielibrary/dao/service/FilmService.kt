package ru.evgeniykravchenko.movielibrary.dao.service

import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.evgeniykravchenko.movielibrary.common.annotation.LogExecution
import ru.evgeniykravchenko.movielibrary.dao.repository.FilmRepository
import ru.evgeniykravchenko.movielibrary.domain.Film
import ru.evgeniykravchenko.movielibrary.domain.FilmType
import ru.evgeniykravchenko.movielibrary.rest.dto.FilmDto
import ru.evgeniykravchenko.movielibrary.rest.filter.FilmFilter
import java.time.LocalDate

@Service
class FilmService(
    private val filmRepository: FilmRepository,
    private val queryFactory: SpringDataQueryFactory,
) {
    fun getByFilter(filmFilter: FilmFilter): List<FilmDto> {
        val nameFilter = filmFilter.filmQuery.firstOrNull { it.fieldName == "name" }
        val typeFilter = filmFilter.filmQuery.firstOrNull { it.fieldName == "type" }
        val yearFilter = filmFilter.filmQuery.firstOrNull { it.fieldName == "year" }
        return queryFactory.listQuery<Film> {
            selectDistinct(entity(Film::class))
            from(entity(Film::class))
            where(
                and(
                    nameFilter?.run { (col(Film::name).like("%${nameFilter.value}%")) },
                    typeFilter?.run { col(Film::type).equal(FilmType.valueOf(typeFilter.value)) },
                    yearFilter?.run {
                        col(Film::releaseDate).between(
                            LocalDate.of(yearFilter.value.toInt(), 1, 1),
                            LocalDate.of(yearFilter.value.toInt(), 12, 31)
                        )
                    }
                )
            )
            offset(filmFilter.offset)
            limit(filmFilter.limit)
        }.map { it.mapToDto() }
    }

    @LogExecution
    fun getById(id: Long): FilmDto {
        val film = filmRepository.findByIdOrNull(id) ?: throw NoSuchElementException("Not found movie with id $id")
        return film.mapToDto()
    }

    fun insertFilmsIntoLibrary(films: List<FilmDto>) {
        filmRepository.saveAll(
            films.map {
                Film(
                    name = it.name,
                    description = it.description,
                    type = FilmType.valueOf(it.type),
                    releaseDate = it.releaseDate
                )
            }
        )
    }
}