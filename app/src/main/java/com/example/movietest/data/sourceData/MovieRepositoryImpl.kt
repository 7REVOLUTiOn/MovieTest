package com.example.movietest.data.sourceData

import android.util.Log
import com.example.movietest.data.retrofit.MovieApi
import com.example.movietest.domain.entity.MovieAll
import com.example.movietest.data.bean.MovieBean
import com.example.movietest.data.bean.MoviesMapper
import com.example.movietest.domain.entity.MovieSingle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class MovieRepositoryImpl(private val api: MovieApi = MovieApi.getInstance()) : IMovieRepository {

    private val beanToEntity = MoviesMapper().beanToEntity
    private val movieSingleBeanToEntity = MoviesMapper().movieSingleBeanToEntity

    override suspend fun getAllMovies(movieName: String): List<MovieAll> =
        withContext(Dispatchers.IO) {
            val result = mutableListOf<MovieBean.Search>()
            kotlin.runCatching {
                var i = 1
                do {
                    val moviesPage = api.getMoviesByPage(movieName, i)
                    result.addAll(moviesPage.search)
                    i++
                    Log.d("pop", "${moviesPage.totalResults.toIntOrNull()}")
                    Log.d("allFilms", "$i")
                } while ((moviesPage.totalResults.toIntOrNull() ?: -1) >= result.size)
            }.getOrElse {
                Log.d("ere", "${it.stackTraceToString()}")
               // null
            }

            //var movies = mutableListOf<Movie>()
            //for (j in 1 until (result.size)){
            // movies.add(Movie(result[j]?.imdbID, result[j]?.poster,result[j]?.title,result[j]?.type,result[j]?.year))
            //}

            val movies: List<MovieAll> = result.mapNotNull {
                beanToEntity(it)
            }


            return@withContext movies//@withContext result
        }

    override suspend fun getMovieById(id: String): MovieSingle? =
        withContext(Dispatchers.IO) {
           return@withContext kotlin.runCatching {
               val rezult = api.getMoviesId(id)
               val mapRezult = movieSingleBeanToEntity(rezult)
               if (mapRezult != null){
                   mapRezult
               }else{
                   throw Exception("Mapping error")
               }
            }.getOrElse {
                Log.d("ere", "${it.stackTraceToString()}")
                null
            }

           // val movie:MovieSingle? =

            //Сделать маппер - на фильм


            //return@withContext movie
        }


            //TODO("ЗАВЕСТИ TRETSULT")
            //TODO("Два разных репозитория + два разных маппера")










        //TODO("Написать логи")

//    val response: String?,
//    val search: List<MovieBean.Search?>,
//    val totalResults: String?,
//    val imdbID: String?,
//    val poster: String?,
//    val title: String?,
//    val type: String?,
//    val year: String?
}