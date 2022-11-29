package ru.evgeniykravchenko.movielibrary.dao.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull
import ru.evgeniykravchenko.movielibrary.domain.Movie
import java.time.LocalDate

@DataJpaTest
class MovieRepositoryTest @Autowired constructor(
    private val entityManager: TestEntityManager,
    private val movieRepository: MovieRepository
) {
    @Test
    fun `when findByIdOrNull then return Movie`() {
        //given
        val movie = Movie(name = "TEST", description = "TEST", releaseDate = LocalDate.of(2022, 1, 1))
        //when
        entityManager.persist(movie)
        entityManager.flush()
        //then
        val found = movieRepository.findByIdOrNull(movie.id)
        assertThat(found).isEqualTo(movie)
    }

}