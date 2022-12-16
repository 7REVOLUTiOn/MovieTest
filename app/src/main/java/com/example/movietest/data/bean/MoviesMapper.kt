package com.example.movietest.data.bean

import android.util.Log
import com.example.movietest.domain.entity.MovieAll
import com.example.movietest.domain.entity.MovieSingle
import java.lang.RuntimeException

class MoviesMapper {

    val beanToEntity: (bean: MovieBean.Search) -> MovieAll? = { it.toEntity() }

    //У мапера две задачи: 1)Проверка обязтальности полей 2)Маппинг в нужный обьект
    private fun MovieBean.Search.toEntity() = runCatching {
        MovieAll(
            imdbID = imdbID,
            poster = poster,
            title = title,
            type = type,
            year = year
        )
    }.getOrElse {
        Log.e("MovieMapper", "${it.stackTraceToString()}")
        null
    }


    val movieSingleBeanToEntity: (bean: MovieById) -> MovieSingle? = { it.toEntity() }

    private fun MovieById.toEntity() = runCatching {
        MovieSingle(
            poster = poster,
            title = title,
            plot = plot,
            director = director,
            genre = genre,
            year = year
        )
    }.getOrElse {
        Log.e("MovieMapper", "${it.stackTraceToString()}")
        null
    }





}