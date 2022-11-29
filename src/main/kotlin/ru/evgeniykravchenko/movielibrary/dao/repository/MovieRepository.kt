package ru.evgeniykravchenko.movielibrary.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.evgeniykravchenko.movielibrary.domain.Movie

/**
 * Репозиторий для работы с сущностью [Movie]
 */
@Repository
interface MovieRepository : JpaRepository<Movie, Long>