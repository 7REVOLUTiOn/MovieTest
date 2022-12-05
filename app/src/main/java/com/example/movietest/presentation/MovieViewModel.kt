package com.example.movietest.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.launch
import androidx.lifecycle.*
import com.example.movietest.data.retrofit.MovieApi
import com.example.movietest.domain.entity.MovieById

class MovieViewModel(
    private var id:String,
    private val application: Application
): ViewModel() {

    private var _rezult = MutableLiveData<MovieById>()
    val rezult: LiveData<MovieById>
        get() = _rezult


    init {
        startFragment()
    }

    private fun startFragment() {

        id = id.replace("","")
        id = id.replace("MovieFragmentArgs{OmdbId=","")
        id = id.replace("{","")
        id = id.replace("}","")

        viewModelScope.launch{
            val retrofit = MovieApi.getInstance()
            _rezult.value = retrofit.getMoviesId(id)
        }


    }


}