package com.example.movietest.data.DataSource

import android.util.Log
import com.example.movietest.data.Retrofit.MovieApi
import com.example.movietest.domain.entity.Movie
import com.example.movietest.domain.entity.MovieBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(private val api: MovieApi = MovieApi.getInstance()) : IMovieRepository {


    override suspend fun getAllMovies(movieName: String): List<MovieBean.Search?> =
        withContext(Dispatchers.IO) {
            val result = mutableListOf<MovieBean.Search?>()
            kotlin.runCatching {
                var i = 1
                do {
                    val moviesPage = api.getMoviesByPage(movieName, i)
                    result.addAll(moviesPage.search)
                    i++
                    Log.d("pop", "${moviesPage.totalResults?.toIntOrNull()}")
                } while ((moviesPage.totalResults?.toIntOrNull() ?: -1) >= result.size)
            }.getOrElse {
                Log.d("ere","${it.stackTraceToString()}")
            }

            return@withContext result

        }


}