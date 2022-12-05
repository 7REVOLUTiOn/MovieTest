package com.example.movietest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.movietest.data.sourceData.IMovieRepository
import com.example.movietest.data.sourceData.MovieRepositoryImpl


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.movietest.R.layout.activity_main)

        //val movieRepository: IMovieRepository = MovieRepositoryImpl()

        //val fragment: Fragment = MoviesRVFragment()

        /*val retrofit = MovieApi.getInstance()
        MainScope().launch {
            Log.d("ioi","${retrofit.getMoviesId("tt0078346")}")
        }*/

//        MainScope().launch {
//           val count = movieRepository.getAllMovies("superman")
//           Log.d("retrofit","${count}")
//           Log.d("retrofit","${count.size}")
//       }

//        TODO("Релазивовать нормально RecyclerView")
//        TODO("Реализовать обработку запросов в data слое и делать запрос через интерфейс")
//        TODO("Релизовать нормально viewBinding")
//        TODO("Реализовать нормально jetpack navigation")
//        TODO("Реализовать нормально viewModel для фрагментов")
//        TODO("Реаллизовать нормально LiveData для фрагментов")
//        TODO("Реализовать нормально фрагенты")
//        TODO("Реализивать нормально корутины")
//        TODO("Сделать приклекательный UI")
//        TODO("Поработать с корутинами для более отзывчего ui")
//        TODO("Можно попробовать реализивать dataBinding")

        /*MainScope().launch {
            Log.d("ioi","${retrofit.getMoviesByPage("superman",1)}")
        }*/
        //replaceFragment(RecyclerViewFragmentFragment)

    }

    private fun replaceFragment(recyclerViewFragmentFragment: Fragment) {
       /* val fragmentManager = supportFragmentManager
        val ragmentTransaction = fragmentManager.beginTransaction()
        ragmentTransaction.replace(R.id.recyclerView,recyclerViewFragmentFragment)
        ragmentTransaction.commit()*/
    }

}