package com.example.movietest.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movietest.data.retrofit.MovieApi
import com.example.movietest.databinding.FragmentMovieFromRecyclerViewBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.lang.RuntimeException


class MovieFragment : Fragment() {

   // private val args by navArgs<MovieFromRecyclerViewArgs>()
    private var _binding: FragmentMovieFromRecyclerViewBinding? = null
    private val binding:FragmentMovieFromRecyclerViewBinding
        get() = _binding?: throw RuntimeException("MovieFragmentBinding == null")



    /*private val viewModelFactory by lazy {
        val args = MovieIdFragmentArgs.fromBundle(requireArguments())
        Log.d("newFragment","${args.omdbId}")
    }*/


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieFromRecyclerViewBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var args = MovieFragmentArgs.fromBundle(requireArguments()).toString()
        Log.d("newFragment","${args}")
        args = args.replace("","")
        args = args.replace("MovieFragmentArgs{OmdbId=","")
        args = args.replace("{","")
        args = args.replace("}","")
        Log.d("321",args)


        MainScope().launch {
            val retrofit = MovieApi.getInstance()
            Log.d("321","${args}")
            val rezult = retrofit.getMoviesId(args.toString())
            binding.titleTextView.text = rezult.title
            Log.d("321","${rezult}")
            binding.plotTextView.text = rezult.plot
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}