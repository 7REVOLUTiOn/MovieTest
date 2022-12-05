package com.example.movietest.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.RuntimeException

class MovieFragmentViewModelFactory(
    private val id:String,
    private val application: Application
):ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)){
            return MovieViewModel(id,application) as T
        }
        throw RuntimeException("Unknown view model class $modelClass")
    }
}