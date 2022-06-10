package com.homework.nasapicture.ui.mars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.FragmentMarsBinding
import com.homework.nasapicture.utils.Date
import com.homework.nasapicture.utils.UNKNOWN_ERROR
import com.homework.nasapicture.viewmodel.MarsState
import com.homework.nasapicture.viewmodel.MarsViewModel


class MarsFragment : Fragment() {
    private var _binding: FragmentMarsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MarsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MarsViewModel::class.java]
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
    }
        val date = Date()
        viewModel.sendRequest("2015-6-3")
    }

    private fun renderData(it: MarsState) {
        when (it) {
            is MarsState.Loading -> {
                binding.imageViewProgress.load(R.drawable.progress_animation)
            }
            is MarsState.Error -> {
                with(binding) {
                    imageViewProgress.visibility = View.GONE
                    binding.nasaPictureImageView.load(R.drawable.error_image)}
            }
            is MarsState.Success -> {
                with(binding) {
                    imageViewProgress.visibility = View.GONE
                    nasaPictureImageView.load(it.marsRoverPhotos.photos[0].imgSrc)
                }
            }

            else -> {
                MarsState.Error(Throwable(UNKNOWN_ERROR))
            }
        }
    }


    companion object {
        fun newInstance() = MarsFragment()
            }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    }
