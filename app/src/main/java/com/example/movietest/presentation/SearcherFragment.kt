package com.example.movietest.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.movietest.databinding.FragmentWelcomeBinding
import java.lang.RuntimeException


class SearcherFragment : Fragment() {

    private var _binding:FragmentWelcomeBinding? = null
    private val binding:FragmentWelcomeBinding
        get() =_binding?:throw RuntimeException("FragmentSearchBiding == null")

    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener(){
            //findNavController().navigate(SearcherFragmentDirections.actionWelcomeFragmentToRecyclerViewFragmentFragment2("superman"))
            //findNavController().navigate(SearcherFragmentDirections.actionWelcomeFragmentToRecyclerViewFragmentFragment2(binding.editText.text.toString()))
            val text = binding.editText.text.toString()
            val action = SearcherFragmentDirections.actionWelcomeFragmentToRecyclerViewFragmentFragment2(text)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}