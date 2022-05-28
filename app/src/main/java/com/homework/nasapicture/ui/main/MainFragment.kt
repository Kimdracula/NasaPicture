package com.homework.nasapicture.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.homework.nasapicture.databinding.FragmentMainBinding
import com.homework.nasapicture.viewmodel.MainState
import com.homework.nasapicture.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.getPictures()
    }

    private fun renderData(it: MainState?) {
        when (it) {
            is MainState.Loading -> {binding.progressIndicator.visibility = View.VISIBLE}
            is MainState.Error -> {}
            is MainState.Success -> {
                binding.progressIndicator.visibility = View.GONE
                binding.nasaPictureImageView.load(it.pictureOfTheDay.url)
            }

            else -> {}
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}