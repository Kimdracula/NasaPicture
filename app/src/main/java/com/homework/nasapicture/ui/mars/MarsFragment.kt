package com.homework.nasapicture.ui.mars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.FragmentMainBinding
import com.homework.nasapicture.databinding.FragmentMarsBinding


class MarsFragment : Fragment() {
    private var _binding: FragmentMarsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = MarsFragment()
            }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    }
