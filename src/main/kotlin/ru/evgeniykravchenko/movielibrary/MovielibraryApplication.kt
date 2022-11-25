package ru.evgeniykravchenko.movielibrary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovielibraryApplication

fun main(args: Array<String>) {
	runApplication<MovielibraryApplication>(*args)
}
