package com.example.movietest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.movietest.R
import com.example.movietest.data.DataSource.IMovieRepository
import com.example.movietest.data.DataSource.MovieRepositoryImpl
import com.example.movietest.data.Retrofit.MovieApi
import com.example.movietest.domain.entity.Movie
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieRepository: IMovieRepository = MovieRepositoryImpl()

        val retrofit = MovieApi.getInstance()
        MainScope().launch {
            Log.d("ioi","${retrofit.getMoviesId("tt0078346")}")
        }

        MainScope().launch {
            val count = movieRepository.getAllMovies("superman")
            Log.d("retrofit","${count}")
            Log.d("retrofit","${count.size}")
        }
    }
}