package ru.evgeniykravchenko.movielibrary.domain

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "t_film")
class Film(
    @Id
    @Column(name = "id")
    val id : Long = 0,

    @Column(name = "name", nullable = false)
    val name : String = "",

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    val type : FilmType = FilmType.FULL,

    @Column(name = "description")
    val description: String = "",

    @Column(name = "release_date")
    val releaseDate: LocalDate = LocalDate.now()
)