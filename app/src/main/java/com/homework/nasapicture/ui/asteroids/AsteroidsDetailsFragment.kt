package com.homework.nasapicture.ui.asteroids

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.homework.nasapicture.databinding.AsteroidDetailsFragmentBinding
import com.homework.nasapicture.model.AsteroidsDTO
import com.homework.nasapicture.model.X20150907
import com.homework.nasapicture.utils.ASTEROIDS_KEY_BUNDLE
import com.homework.nasapicture.viewmodel.AsteroidDetailsViewModel
import com.homework.nasapicture.viewmodel.AsteroidsViewModel


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
        arguments?.getParcelable<X20150907>(ASTEROIDS_KEY_BUNDLE)?.let {
            viewModel.getAsteroidsDetails(it)
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
