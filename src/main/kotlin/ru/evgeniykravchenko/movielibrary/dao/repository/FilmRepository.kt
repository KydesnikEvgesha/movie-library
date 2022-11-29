package ru.evgeniykravchenko.movielibrary.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.evgeniykravchenko.movielibrary.domain.Film
import ru.evgeniykravchenko.movielibrary.domain.FilmType
import java.time.LocalDate

@Repository
interface FilmRepository : JpaRepository<Film, Long>