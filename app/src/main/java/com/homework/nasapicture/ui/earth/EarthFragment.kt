package com.homework.nasapicture.ui.earth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.FragmentEarthBinding
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.homework.nasapicture.utils.Date
import com.homework.nasapicture.viewmodel.EarthState
import com.homework.nasapicture.viewmodel.EarthViewModel


class EarthFragment : Fragment() {
    private var _binding: FragmentEarthBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EarthViewModel
    private var datePath = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[EarthViewModel::class.java]

        binding.datePicker.setOnDateChangedListener { datePicker, year, month, day ->
            viewModel.sendRequest("$year-${month + 1}-$day")
            datePath = "$year/${month + 1}/$day"
            viewModel.getLiveData().observe(viewLifecycleOwner) {
                renderData(it)
            }
        }

    }
    private fun renderData(it: EarthState) {
        when (it) {
            is EarthState.Loading -> {
                binding.imageViewProgress.load(R.drawable.progress_animation)
            }
            is EarthState.Error -> {
                with(binding) {
                    imageViewProgress.visibility = View.GONE
                    binding.earthPictureImageView.load(R.drawable.error_image)
                }
            }
            is EarthState.Success -> {
                with(binding) {
                    imageViewProgress.visibility = View.GONE
                    if (it.earthPhotos[0].image == null) {
                        earthPictureImageView.load(R.drawable.error_image)
                    } else {
                        earthPictureImageView.load("https://api.nasa.gov/EPIC/archive/natural/${datePath}/png/${it.earthPhotos[0].image}" +
                                ".png?api_key=${com.homework.nasapicture.BuildConfig.NASA_API_KEY}")
                        textViewCaption.text = it.earthPhotos[0].caption
                        textViewCoordinates.text =
                            ("lat = ${it.earthPhotos[0].centroidCoordinates.lat}; lon = ${it.earthPhotos[0].centroidCoordinates.lon}")
                    }


                }
            }
        }
    }

    companion object {
        fun newInstance() = EarthFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
