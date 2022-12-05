package com.example.movietest.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.RuntimeException

class MoviesViewModelFactory(
    private var args:String,
    private val application: Application
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesRVViewModel::class.java)){
            return MoviesRVViewModel(args,application) as T
        }
        throw RuntimeException("Unknown view model class $modelClass")
    }
}