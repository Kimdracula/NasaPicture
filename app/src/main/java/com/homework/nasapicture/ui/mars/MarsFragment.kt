package com.homework.nasapicture.ui.mars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.FragmentMarsBinding
import com.homework.nasapicture.utils.Date
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
        viewModel.sendRequest(date.formattedYesterday, "spirit")

        binding.datePicker.setOnDateChangedListener { datePicker, year, month, day ->
            viewModel.sendRequest("$year-${month + 1}-$day", "spirit")
        }


    }


    private fun renderData(it: MarsState) {
        when (it) {
            is MarsState.Loading -> {
                binding.imageViewProgress.load(R.drawable.progress_animation)
            }
            is MarsState.Error -> {
                with(binding) {
                    imageViewProgress.visibility = View.GONE
                    binding.marsPictureImageView.load(R.drawable.error_image)
                }
            }
            is MarsState.Success -> {
                with(binding) {
                    imageViewProgress.visibility = View.GONE


                    if (it.marsRoverPhotos.photos.isEmpty()) {
                        binding.marsPictureImageView.load(R.drawable.error_image)
                    } else {
                        marsPictureImageView.load(it.marsRoverPhotos.photos[0].imgSrc)}


                    if (it.marsRoverPhotos.photos[0].camera.fullName != null) {
                        textViewCamera.text =
                            ("Камера: ${it.marsRoverPhotos.photos[0].camera.fullName}")
                    }

                    if (it.marsRoverPhotos.photos[0].rover.launchDate != null) {
                        textViewLaunch.text =
                            ("Дата запуска: ${it.marsRoverPhotos.photos[0].rover.launchDate}")
                    }

                    if (it.marsRoverPhotos.photos[0].rover.landingDate != null) {
                        textViewLanding.text =
                            ("Дата посадки: ${it.marsRoverPhotos.photos[0].rover.landingDate}")
                    }


                }
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
