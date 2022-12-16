package com.example.movietest.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietest.R
import com.example.movietest.domain.entity.MovieAll


class MoviesRVFragment : Fragment() {

    //private lateinit var rezult:List<MovieBean.Search?>
    //val movieRepository: IMovieRepository = MovieRepositoryImpl()


    //var args = MoviesRVFragmentArgs.fromBundle(requireArguments()).toString()


    private val viewModelFactory by lazy {
        //var args = MoviesRVFragmentArgs.fromBundle(requireArguments()).toString()
        val args: MoviesRVFragmentArgs by navArgs()
        var text = args.searchName
        MoviesViewModelFactory(text, requireActivity().application)
    }

    private val viewModel: MoviesRVViewModel by lazy {
        Log.d("viewModel", "fragment: инициализируем viewModel")
        ViewModelProvider(this, viewModelFactory)[MoviesRVViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        Log.d("viewModel", "fragment: Старт подписок ")
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

    private fun observeViewModel(view: View) {
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        Log.d("viewModel", "fragment: запуск функции подписок")
        //Log.d("viewModel", "$progressBar")


        viewModel.isLoadingRV.observe(viewLifecycleOwner) {
            Log.d(
                "viewModel",
                "fragment: прилетел обьект isLoading -> старт iewModel.isLoadingRV.observe"
            )
            Log.d("viewModel", "fragment: isLoading $it")
            if (it == false) {
                progressBar.isVisible = false
            }
            if (it == true) {
                progressBar.isVisible = true
            } else {
                Log.d("viewModel", "Вот зедсь приколы ")
            }
        }


        viewModel.rezult1.observe(viewLifecycleOwner) {
            Log.d(
                "viewModel",
                "fragment: прилетел обьект rezult -> старт viewModel.rezult1.observe"
            )
            setupRecyclerView(view, it)
            Log.d("viewModel", "fragment: установили recyclerView")

        }

        //Log.d("viewModel","закончили с одним подписчиком, приступаем к другому")


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

    private fun setupRecyclerView(view: View, rezult: List<MovieAll>) {
        val layoutManager = LinearLayoutManager(context)
        val rvMovieList: RecyclerView = view.findViewById(R.id.recyclerView)
        rvMovieList.layoutManager = layoutManager
        val recyclerViewAdapter = RecyclerViewAdapter(rezult)//rezult передаётся
        rvMovieList.adapter = recyclerViewAdapter
        Log.d("egor", "adapter = ${rvMovieList.adapter}")
        Log.d("viewModel", "fragment: установка recyclerView")


        recyclerViewAdapter.onMovieItemClickListener = {
            // it - это то что соденржит наш MovieBean (RecyclerView Item)
            val id = it?.imdbID //Получаем id через
            Log.i("click", "${it}")
            //findNavController(holder.view).navigate(R.id.action_recyclerViewFragmentFragment2_to_movieFromRecyclerView)
            val action =
                MoviesRVFragmentDirections.actionRecyclerViewFragmentFragment2ToMovieFromRecyclerView(
                    it.imdbID.toString()
                )

            findNavController().navigate(
                action
            ) //Посомтреть будет ли работать если передать сразу id
        }
    }
}

