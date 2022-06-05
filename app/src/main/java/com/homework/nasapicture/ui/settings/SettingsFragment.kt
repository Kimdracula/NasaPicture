package com.homework.nasapicture.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.homework.nasapicture.databinding.FragmentSettingsBinding
import com.homework.nasapicture.ui.main.MainFragment

class SettingsFragment : Fragment() {


    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {
        fun newInstance() = SettingsFragment()
        private var isMain = true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}