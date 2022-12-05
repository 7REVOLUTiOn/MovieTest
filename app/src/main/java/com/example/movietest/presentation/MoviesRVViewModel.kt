package com.example.movietest.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.movietest.data.sourceData.IMovieRepository
import com.example.movietest.data.sourceData.MovieRepositoryImpl
import com.example.movietest.domain.entity.MovieBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MoviesRVViewModel(
    private var args: String,
    private val application: Application,
): ViewModel() {

    init {
        startFragment()
    }

    private val _rezult1 = MutableLiveData<List<MovieBean.Search?>>()
    val rezult1: LiveData<List<MovieBean.Search?>>
            get()=_rezult1

    private fun startFragment() {
        args = args.replace("MoviesRVFragmentArgs{SearchName=","")
        args = args.replace("}","")
        val movieRepository: IMovieRepository = MovieRepositoryImpl()
        var rezult:List<MovieBean.Search?>

        viewModelScope.launch {
            rezult = movieRepository.getAllMovies(args)
            _rezult1.value = rezult
        }
    }


}