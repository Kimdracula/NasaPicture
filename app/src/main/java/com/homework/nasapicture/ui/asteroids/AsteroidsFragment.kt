package com.homework.nasapicture.ui.asteroids

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homework.nasapicture.databinding.FragmentAsteroidsBinding



class AsteroidsFragment : Fragment() {

    private var _binding: FragmentAsteroidsBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAsteroidsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        fun newInstance() = AsteroidsFragment()
                }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    }