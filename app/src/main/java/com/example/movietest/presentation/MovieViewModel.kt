package com.example.movietest.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.*
import com.example.movietest.data.sourceData.IMovieRepository
import com.example.movietest.data.sourceData.MovieRepositoryImpl
import com.example.movietest.domain.entity.MovieSingle

class MovieViewModel(
    private var id:String,
    private val application: Application
): ViewModel() {

    private val _rezult = MutableLiveData<MovieSingle>()
    val rezult: LiveData<MovieSingle> = _rezult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean> = _isLoading


    init {
        startFragment()
    }

    private fun startFragment() {
        val movieRepository: IMovieRepository = MovieRepositoryImpl()

        //TODO("Получать Bundle (или можно нормально через safeArgs), liveData для загрузки (анимации) ")
        //Загрузка говорить грузится или не грузится, а уже сам фрагмент реагируует на эту лавй дату
        //И сам рашает, что ему делать
        //TODO("Реализивовать обьектный класс movie, который будет создаваться путем конвертации (movieBean -> обьектный класс) в data слое. И именно уже обьект
        // передавать во вью модели. Также для передачи нужно создать посредника (функцию), через которую будет обращаться presentation слой к data слою.
        // (presentation -> domain (fun) -> data")


        viewModelScope.launch{

            _isLoading.value = true
            Log.d("debugginh","${movieRepository.getMovieById(id)}")
            _rezult.value = movieRepository.getMovieById(id)
            _isLoading.value = false
        }
    }


}