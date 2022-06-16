package com.homework.nasapicture.ui.asteroids

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.AsteroidDetailsFragmentBinding
import com.homework.nasapicture.model.X20150907
import com.homework.nasapicture.utils.ASTEROIDS_KEY_BUNDLE
import com.homework.nasapicture.viewmodel.AsteroidDetailsViewModel
import com.homework.nasapicture.viewmodel.AsteroidsDetailsState


class AsteroidsDetailsFragment: Fragment() {
    private var _binding: AsteroidDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AsteroidDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AsteroidDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<X20150907>(ASTEROIDS_KEY_BUNDLE)?.let { it ->
            viewModel = ViewModelProvider(this)[AsteroidDetailsViewModel::class.java]
            viewModel.getAsteroidsDetails(it)
            viewModel.getLiveData().observe(viewLifecycleOwner){
                renderData(it)
            }
        }
    }

    private fun renderData(it: AsteroidsDetailsState?) {
        when (it) {
            is AsteroidsDetailsState.Loading -> {
            //    binding.imageViewProgress.load(R.drawable.progress_animation)
            }
            is AsteroidsDetailsState.Error -> {
                with(binding) {
                   // imageViewProgress.visibility = View.GONE
                    binding.imageViewAsteroidStatus.load(R.drawable.error_image)
                }
            }
            is AsteroidsDetailsState.Success -> {
                with(binding) {
if (it.asteroids.isPotentiallyHazardousAsteroid){
    imageViewAsteroidStatus.load(R.drawable.asteroid_hazardous)}
    else{imageViewAsteroidStatus.load(R.drawable.asteroid_safe)}

textViewCloseApproachDate.text = it.asteroids.closeApproachData[0].closeApproachDateFull
                    textViewAbsoluteMagnitude.text = it.asteroids.absoluteMagnitudeH.toString()
                    textViewEstimatedDiameter.text = it.asteroids.estimatedDiameter.kilometers.toString()
                    textViewRelativeVelocity.text = it.asteroids.closeApproachData[0].relativeVelocity.kilometersPerHour
                    textViewDistanceFromEarth.text=it.asteroids.closeApproachData[0].missDistance.kilometers
                }
            }
        }
    }
    companion object {

        fun newInstance(bundle: Bundle): AsteroidsDetailsFragment {
            val fragment = AsteroidsDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
