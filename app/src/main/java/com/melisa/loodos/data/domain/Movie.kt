package com.melisa.loodos.data.domain


import com.squareup.moshi.Json



class Movie (
   
    @field:Json(name = "Title") val title: String,
    @field:Json(name = "Year") val year: String,
    @field:Json(name = "Rated") val rated: String,
    @field:Json(name = "Released") val released: String,
    @field:Json(name = "Runtime") val runtime: String,
    @field:Json(name = "Genre") val genre: String,
    @field:Json(name = "Director") val director: String,
    @field:Json(name = "Writer") val writer: String,
    @field:Json(name = "Actors") val actors: String,
    @field:Json(name = "Plot") val plot: String,
    @field:Json(name = "Language" )val language: String,
    @field:Json(name = "Country") val country: String,
    @field:Json(name = "Awards") val awards: String,
    @field:Json(name = "Poster") val poster: String,
    @field:Json(name = "Ratings") val ratings: List<Rating>,
    @field:Json(name = "Metascore") val metaScore: String,
    @field:Json(name = "imdbRating") val imdbRating: String,
    @field:Json(name = "imdbVotes") val imdbVotes: String,
    @field:Json(name = "imdbID") val imdbId: String,
    @field:Json(name = "Type") val type: String,
    @field:Json(name = "DVD") val dvd: String,
    @field:Json(name = "BoxOffice") val boxOffice: String,
    @field:Json(name = "Production") val production: String,
    @field:Json(name = "Website") val website: String,
    @field:Json(name = "Response") val response: String
)