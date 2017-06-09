package com.md.saklia.data

case class Film(filmId: Int, title: String, description: String, releaseYear: Int, rentalRate: BigDecimal)
case class Category(categoryId: Int, name: String)
case class FilmCategory(filmId: Int, categoryId: Int)
