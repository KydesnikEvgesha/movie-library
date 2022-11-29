package ru.evgeniykravchenko.movielibrary.domain

import ru.evgeniykravchenko.movielibrary.rest.dto.FilmDto
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "t_film")
class Film(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "name", length = 255, nullable = false)
    val name: String = "",

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    val type: FilmType = FilmType.FULL,

    @Column(name = "description")
    val description: String = "",

    @Column(name = "release_date")
    val releaseDate: LocalDate = LocalDate.now()
) {
    fun mapToDto(): FilmDto = FilmDto(
        name,
        type,
        description,
        releaseDate
    )
}