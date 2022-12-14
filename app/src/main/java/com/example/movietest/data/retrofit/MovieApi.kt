package com.example.movietest.data.retrofit


import com.example.movietest.BuildConfig
import com.example.movietest.data.sourceData.MyOkHttp
import com.example.movietest.domain.entity.MovieBean
import com.example.movietest.domain.entity.MovieById
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface MovieApi {
    @GET("https://www.omdbapi.com/")
    suspend fun getMoviesByPage(
        @Query("s") searchText: String,
        @Query("page") page: Int = 1,
        @Query("apikey") apiKey: String = API_KEY
    ): MovieBean


    @GET("https://www.omdbapi.com/")
    suspend fun getMoviesId(
        @Query("i")searchText: String,
        @Query("apikey") apiKey: String = API_KEY
    ):MovieById


    companion object {
        private const val API_KEY = "89335d00"
        private var movieApi: MovieApi? = null
        fun getInstance(): MovieApi {
            if (movieApi == null) {
                val okHttpClient = MyOkHttp(
                    isSafe = !BuildConfig.DEBUG
                ).get()
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl("https://www.omdbapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                movieApi = retrofit.create(MovieApi::class.java)
            }
            return movieApi!!
        }
    }
}
//https://www.omdbapi.com/?&s=superman&apikey=89335d00
//https://www.omdbapi.com/