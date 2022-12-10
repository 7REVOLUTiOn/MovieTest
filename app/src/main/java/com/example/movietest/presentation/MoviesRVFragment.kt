package com.example.movietest.presentation

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietest.R
import com.example.movietest.data.sourceData.IMovieRepository
import com.example.movietest.data.sourceData.MovieRepositoryImpl

import com.example.movietest.domain.entity.MovieBean
import kotlinx.coroutines.launch


class MoviesRVFragment : Fragment() {

    //private lateinit var rezult:List<MovieBean.Search?>
    //val movieRepository: IMovieRepository = MovieRepositoryImpl()


    //var args = MoviesRVFragmentArgs.fromBundle(requireArguments()).toString()



   private val viewModelFactory by lazy {
       var args = MoviesRVFragmentArgs.fromBundle(requireArguments()).toString()
       MoviesViewModelFactory(args,requireActivity().application)
   }

   private val viewModel:MoviesRVViewModel by lazy {
       ViewModelProvider(this,viewModelFactory)[MoviesRVViewModel::class.java]
   }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view_fragment, container, false)
    }

    //

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        observeViewModel(view)
        //launchViewModel(args,requireActivity().application)

        //Log.d("egor","ergs без обработки${args}")
        //args = args.replace("MoviesRVFragmentArgs{SearchName=","")
        //args = args.replace("}","")
        //args = args.replace(" ","")
        //Log.d("egor","args = ${args}")



        //Принимаем из контекста
//     -----------------------------------------------------------------------------
//        val layoutManager = LinearLayoutManager(context)
//        recyclerView = view.findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = layoutManager
//        recyclerView.setHasFixedSize(true)
        //-------------------------------------------------------------------------
//          viewLifecycleOwner.lifecycleScope.launch { //Вот так вот для фрагментов
//            rezult = movieRepository.getAllMovies(args)
//              Log.d("egor","args = ${args}")
//              Log.d("egor","rezult = ${rezult}")
//              //adapter = RecyclerViewAdapter(rezult)
//             // recyclerView.adapter = adapter
//              setupRecyclerView(view,rezult)
        }

    private fun observeViewModel(view:View) {
        val progressBar:ProgressBar = view.findViewById(R.id.progressBar)
        viewModel.rezult1.observe(viewLifecycleOwner){
            Log.d("EW","Вот здесь еще что то есть")
            progressBar.isVisible = false
            setupRecyclerView(view,it)
    }

    //TODO("Есть ли смысл реализовывать передачу данных через notifyDataSetChanged)

        //findNavController().navigate(RecyclerViewFragmentDirections.actionRecyclerViewFragmentFragment2ToMovieFromRecyclerView())


    }



    /*private fun dataInitialize():List<MovieBean.Search?>{ //Будем распакоывать rezult

        var rezult:List<MovieBean.Search?>
        val retrofit = MovieApi.getInstance()
        MainScope().launch {
             rezult = movieRepository.getAllMovies("superman")
             return rezult
        }
    }*/

    private fun setupRecyclerView(view: View, rezult: List<MovieBean.Search?>) {
        val layoutManager = LinearLayoutManager(context)
        val rvMovieList:RecyclerView = view.findViewById(R.id.recyclerView)
        rvMovieList.layoutManager = layoutManager
        val recyclerViewAdapter = RecyclerViewAdapter(rezult)//rezult передаётся
        rvMovieList.adapter = recyclerViewAdapter
        Log.d("egor","adapter = ${rvMovieList.adapter}")


        recyclerViewAdapter.onMovieItemClickListener = {
            // it - это то что соденржит наш MovieBean (RecyclerView Item)
            val id = it?.imdbID //Получаем id через
            Log.i("click", "${it}")
            //findNavController(holder.view).navigate(R.id.action_recyclerViewFragmentFragment2_to_movieFromRecyclerView)
            findNavController().navigate(
                MoviesRVFragmentDirections.actionRecyclerViewFragmentFragment2ToMovieFromRecyclerView(
                    it?.imdbID.toString())) //Посомтреть будет ли работать если передать сразу id
        }
    }
}

