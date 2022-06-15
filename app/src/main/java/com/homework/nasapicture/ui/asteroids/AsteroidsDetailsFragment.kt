package com.homework.nasapicture.ui.asteroids

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.homework.nasapicture.databinding.AsteroidDetailsFragmentBinding


class AsteroidsDetailsFragment: Fragment()  {
    private var _binding: AsteroidDetailsFragmentBinding? = null
    private val binding get() = _binding!!

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


    companion object {

        fun newInstance() = AsteroidsDetailsFragment()
    }
}
