package ru.evgeniykravchenko.movielibrary.domain

import ru.evgeniykravchenko.movielibrary.rest.dto.MovieDto
import java.time.LocalDate
import javax.persistence.*

/**
 * Объект фильм. Содержит в себе информацию по фильму
 *
 * @property id идентификатор
 * @property name название
 * @property type тип [MovieType] по умолчанию [MovieType.FULL]
 * @property description описание
 * @property releaseDate дата выхода
 */
@Entity
@Table(name = "t_movie")
class Movie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "name", length = 255, nullable = false)
    val name: String = "",

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    val type: MovieType = MovieType.UNDEFINED,

    @Column(name = "description")
    val description: String = "",

    @Column(name = "release_date")
    val releaseDate: LocalDate = LocalDate.now()
) {
    fun mapToDto(): MovieDto = MovieDto(
        name,
        type.description,
        description,
        releaseDate
    )
}