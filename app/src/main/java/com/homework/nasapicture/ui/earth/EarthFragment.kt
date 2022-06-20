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
import com.homework.nasapicture.BuildConfig
import com.homework.nasapicture.utils.Date
import com.homework.nasapicture.viewmodel.EarthState
import com.homework.nasapicture.viewmodel.EarthViewModel


class EarthFragment : Fragment() {
    private var _binding: FragmentEarthBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EarthViewModel

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
        viewModel.sendRequest()
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
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
                    val strDate = it.earthPhotos.last().date.split(" ").first()
                    val image = it.earthPhotos.last().image
                    val url = "https://api.nasa.gov/EPIC/archive/natural/" +
                            strDate.replace("-","/",true) +
                            "/png/" +
                            "$image" +
                            ".png?api_key=${BuildConfig.NASA_API_KEY}"
                    earthPictureImageView.load(url)
                        textViewCaption.text = it.earthPhotos.last().caption
                        textViewCoordinates.text =
                            ("lat = ${it.earthPhotos.last().centroidCoordinates.lat}; lon = ${it.earthPhotos.last().centroidCoordinates.lon}")
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
