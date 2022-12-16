package com.example.movietest.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.movietest.data.sourceData.IMovieRepository
import com.example.movietest.data.sourceData.MovieRepositoryImpl
import com.example.movietest.domain.entity.MovieAll
import kotlinx.coroutines.launch

class MoviesRVViewModel(
    private var args: String,
    private val application: Application,
): ViewModel() {

    private val _rezult1 = MutableLiveData<List<MovieAll>>()
    val rezult1: LiveData<List<MovieAll>>
        get()=_rezult1

    val _isLoadingRV = MutableLiveData<Boolean>()
    val isLoadingRV: LiveData<Boolean> = _isLoadingRV

    init {
        startFragment()
    }

    private fun startFragment() {
        //TODO("Тоже самое что и в movie")
        //val movieRepository: IMovieRepository = MovieRepositoryImpl()
        val getMovieByNameUseCase: suspend(name:String) -> List<MovieAll> = MovieRepositoryImpl()::getAllMovies
        // -> 32 строка - можно закоментить и попробовать сделать как я это хотел
        var rezult:List<MovieAll>
        Log.d("viewModel","viewModel: запуск viewModel")



        viewModelScope.launch {
            Log.d("viewModel","viewModel: запуск корутин")
            Log.d("viewModel","View model: проверка isLoading $_isLoadingRV")

            _isLoadingRV.value = true

            rezult = getMovieByNameUseCase(args)

            Log.d("rezult","$rezult")

            _rezult1.value = rezult

            _isLoadingRV.value = false
        }

    }

}