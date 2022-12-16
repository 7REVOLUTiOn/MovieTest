package com.example.movietest.data.sourceData

import com.example.movietest.domain.entity.MovieAll
import com.example.movietest.domain.entity.MovieSingle

interface IMovieRepository {
    suspend fun getAllMovies(movieName: String): List<MovieAll>
    suspend fun getMovieById(id:String):MovieSingle?
}