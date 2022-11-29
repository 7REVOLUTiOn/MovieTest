package com.example.movietest.data.DataSource

import com.example.movietest.domain.entity.MovieBean

interface IMovieRepository {
    suspend fun getAllMovies(movieName: String): List<MovieBean.Search?>
}