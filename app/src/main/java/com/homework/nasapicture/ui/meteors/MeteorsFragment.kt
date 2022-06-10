package com.homework.nasapicture.ui.meteors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.FragmentMarsBinding
import com.homework.nasapicture.databinding.FragmentMeteorsBinding


class MeteorsFragment : Fragment() {

    private var _binding: FragmentMeteorsBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMeteorsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        fun newInstance() = MeteorsFragment()
                }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    }