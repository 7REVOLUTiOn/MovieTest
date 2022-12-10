package com.example.movietest.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.movietest.R
import com.example.movietest.data.retrofit.MovieApi
import com.example.movietest.databinding.FragmentMovieFromRecyclerViewBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.lang.RuntimeException


class MovieFragment : Fragment() {

   // private val args by navArgs<MovieFromRecyclerViewArgs>()
    private var _binding: FragmentMovieFromRecyclerViewBinding? = null
    private val binding:FragmentMovieFromRecyclerViewBinding
        get() = _binding?: throw RuntimeException("MovieFragmentBinding == null")


    private val viewModelFactory by lazy{
        val args = MovieFragmentArgs.fromBundle(requireArguments()).toString()
        MovieFragmentViewModelFactory(args, requireActivity().application)
    }

    private val viewModel:MovieViewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[MovieViewModel::class.java]
    }


    /*private val viewModelFactory by lazy {
        val args = MovieIdFragmentArgs.fromBundle(requireArguments())
        Log.d("newFragment","${args.omdbId}")
    }*/


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieFromRecyclerViewBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*var args = MovieFragmentArgs.fromBundle(requireArguments()).toString()
        Log.d("newFragment","${args}")
        args = args.replace("","")
        args = args.replace("MovieFragmentArgs{OmdbId=","")
        args = args.replace("{","")
        args = args.replace("}","")
        Log.d("321",args)*/



        observeViewModel(view)

//        MainScope().launch {
//            val retrofit = MovieApi.getInstance()
//            Log.d("321","$args")
//            val rezult = retrofit.getMoviesId(args)
//            binding.titleTextView.text = rezult.title
//            Log.d("321","${rezult}")
//            binding.plotTextView.text = rezult.plot
//        }
    }

    private fun observeViewModel(view:View){
        val progressBar:ProgressBar = binding.progressBar
        viewModel.rezult.observe(viewLifecycleOwner){
            progressBar.isVisible = false
            Picasso.get().load(it.poster).fit().centerInside().into(binding.posterImageView)
            binding.titleTextView.text = it.title
            binding.plotTextView.text = it.plot
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}