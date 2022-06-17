package com.homework.nasapicture.ui.earth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.FragmentEarthBinding
import com.homework.nasapicture.viewmodel.EarthImageViewModel
import com.homework.nasapicture.viewmodel.EarthState
import com.homework.nasapicture.viewmodel.EarthViewModel


class EarthFragment : Fragment() {
    private var _binding: FragmentEarthBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EarthViewModel
    private lateinit var viewModelImage: EarthImageViewModel
    private lateinit var newYear: String
    private lateinit var newMonth: String
    private lateinit var newYDay: String


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
        viewModelImage = ViewModelProvider(this)[EarthImageViewModel::class.java]


        binding.datePicker.setOnDateChangedListener { datePicker, year, month, day ->
newYear =year.toString()
            newMonth = month.toString()
            newYDay = day.toString()
            viewModel.sendRequest("$year-${month + 1}-$day")
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


                        viewModelImage.sendRequestImage(newYear,newMonth,newYDay, "png",it.earthPhotos[0].image)
                        viewModelImage.getLiveData().observe(viewLifecycleOwner) {
                            loadImage(it)
                        }
                        textViewCaption.text = it.earthPhotos[0].caption
                        textViewCoordinates.text =
                            ("lat = ${it.earthPhotos[0].centroidCoordinates.lat}; lon = ${it.earthPhotos[0].centroidCoordinates.lon}")
                    }


                }
            }
        }
    }

    private fun loadImage(it: EarthState?) {
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
                        earthPictureImageView.load(R.drawable.error_image)}else{
                        earthPictureImageView.load(it.earthPhotos)
                    }}}}}


    companion object {
        fun newInstance() = EarthFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
