package ru.evgeniykravchenko.movielibrary.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.evgeniykravchenko.movielibrary.domain.Film

@Repository
interface FilmRepository : JpaRepository<Film, Long>