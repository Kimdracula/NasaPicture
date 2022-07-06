package com.homework.nasapicture.ui.picture_of_the_day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.FragmentYesterdayBinding
import com.homework.nasapicture.utils.Date
import com.homework.nasapicture.utils.ImageScale
import com.homework.nasapicture.utils.RainbowText
import com.homework.nasapicture.utils.UNKNOWN_ERROR
import com.homework.nasapicture.viewmodel.POTDState
import com.homework.nasapicture.viewmodel.MainViewModel

class YesterdayFragment:Fragment() {

    var isImageClicked: Boolean = false
    private var _binding: FragmentYesterdayBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentYesterdayBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)}
        val date = Date()
        viewModel.sendRequest(date.formattedYesterday)
        setBottomSheetBehavior(binding.includeBottomSheet.bottomSheetContainer)

    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
    }



    private fun renderData(it: POTDState?) {
        when (it) {
            is POTDState.Loading -> {
                  binding.imageViewProgress.load(R.drawable.progress_animation)
            }
            is POTDState.Error -> {
                with(binding) {
                    imageViewProgress.visibility = View.GONE
                    nasaPictureImageView.load(R.drawable.error_image)}
            }
            is POTDState.Success -> {
                with(binding) {
                    imageViewProgress.visibility = View.GONE
                    if (it.pictureOfTheDay.mediaType == "video")
                    {nasaPictureImageView.load(R.drawable.no_image)}else{
                        nasaPictureImageView.load(it.pictureOfTheDay.url)}
                       includeBottomSheet.bottomSheetDescriptionHeader.text = it.pictureOfTheDay.title
                      includeBottomSheet.bottomSheetDescription.text = RainbowText().makeRainbow(it.pictureOfTheDay.explanation)
                }
                setImageScale()
            }

            else -> {
                POTDState.Error(Throwable(UNKNOWN_ERROR))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setImageScale() {
        binding.nasaPictureImageView.setOnClickListener {
            with(binding) {
                ImageScale().scale(nasaPictureImageView, root)
            }
            isImageClicked = !isImageClicked
            binding.nasaPictureImageView.scaleType = if (isImageClicked) {
                ImageView.ScaleType.CENTER_CROP
            } else {
                ImageView.ScaleType.CENTER_INSIDE
            }
        }
    }

    companion object {
        fun newInstance() = YesterdayFragment()
    }
}