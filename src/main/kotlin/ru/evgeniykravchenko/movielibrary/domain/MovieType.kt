package ru.evgeniykravchenko.movielibrary.domain

/**
 * Тип пленки
 *
 * @property description тип фильма
 */
enum class MovieType(val description: String) {
    /** Полнометражный */
    FULL("Полнометражный"),
    /** Короткометражный */
    SHORT("Короткометражный"),
    /** Сериал */
    SERIES("Сериал"),
    /** Не определен */
    UNDEFINED("Не определен")
}
