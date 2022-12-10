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

    private val _rezult = MutableLiveData<MovieById>()
    val rezult: LiveData<MovieById> = _rezult



    init {
        startFragment()
    }

    private fun startFragment() {

        id = id.replace("","")
        id = id.replace("MovieFragmentArgs{OmdbId=","")
        id = id.replace("{","")
        id = id.replace("}","")

        //TODO("Получать Bundle (или можно нормально через safeArgs), liveData для загрузки (анимации) ")
        //Загрузка говорить грузится или не грузится, а уже сам фрагмент реагируует на эту лавй дату
        //И сам рашает, что ему делать
        //TODO("Реализивовать обьектный класс movie, который будет создаваться путем конвертации (movieBean -> обьектный класс) в data слое. И именно уже обьект
        // передавать во вью модели. Также для передачи нужно создать посредника (функцию), через которую будет обращаться presentation слой к data слою.
        // (presentation -> domain (fun) -> data")

        viewModelScope.launch{
            val retrofit = MovieApi.getInstance()
            _rezult.value = retrofit.getMoviesId(id)
        }
        

    }


}