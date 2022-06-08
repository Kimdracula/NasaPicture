package com.homework.nasapicture.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.FragmentTdbyBinding
import com.homework.nasapicture.utils.Date
import com.homework.nasapicture.utils.UNKNOWN_ERROR
import com.homework.nasapicture.viewmodel.MainState
import com.homework.nasapicture.viewmodel.MainViewModel

class TDBYFragment:Fragment() {

    private var _binding: FragmentTdbyBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTdbyBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)}
        val date = Date()
        viewModel.sendRequest(date.formattedDby)

    }

    private fun renderData(it: MainState?) {
        when (it) {
            is MainState.Loading -> {
                //  binding.imageViewProgress.load(R.drawable.progress_animation)
            }
            is MainState.Error -> {
                with(binding) {
                    // imageViewProgress.visibility = View.GONE
                    binding.nasaPictureImageView.load(R.drawable.error_image)}
            }
            is MainState.Success -> {
                with(binding) {
                    //  imageViewProgress.visibility = View.GONE
                    nasaPictureImageView.load(it.pictureOfTheDay.url)
                    //   includeBottomSheet.bottomSheetDescriptionHeader.text = it.pictureOfTheDay.title
                    //   includeBottomSheet.bottomSheetDescription.text = it.pictureOfTheDay.explanation
                }
            }

            else -> {
                MainState.Error(Throwable(UNKNOWN_ERROR))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        fun newInstance() = TDBYFragment()
    }
}