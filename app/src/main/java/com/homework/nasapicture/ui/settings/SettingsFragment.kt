package com.homework.nasapicture.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.FragmentSettingsBinding
import com.homework.nasapicture.ui.main.MainActivity

class SettingsFragment : Fragment() {
    private lateinit var mainActivity: MainActivity

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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (mainActivity.getNewTheme()) {
            R.style.Theme_Dark -> binding.switchDarkTheme.isChecked = true
            R.style.Theme_Light -> binding.switchLightTheme.isChecked = true
            R.style.Theme_Green -> binding.switchGreenTheme.isChecked = true
        }

        setTheme()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = requireActivity() as MainActivity
    }

    private fun setTheme() {
        with(binding) {

            switchDarkTheme.setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    if (mainActivity.getNewTheme() != R.style.Theme_Dark) {
                        mainActivity.setNewTheme(R.style.Theme_Dark)
                    }
                    disableLightAndGreenTheme()
                } else {
                    if (mainActivity.getNewTheme() != R.style.Theme_NasaPicture) {
                        mainActivity.setNewTheme(R.style.Theme_NasaPicture)
                    }
                    disableLightAndGreenTheme()
                }
            }


            switchLightTheme.setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    if (mainActivity.getNewTheme() != R.style.Theme_Light) {
                        mainActivity.setNewTheme(R.style.Theme_Light)
                    }
                    disableDarkAndGreenTheme()
                } else {
                    if (mainActivity.getNewTheme() != R.style.Theme_NasaPicture) {
                        mainActivity.setNewTheme(R.style.Theme_NasaPicture)
                    }
                    disableDarkAndGreenTheme()
                }
            }


            switchGreenTheme.setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    if (mainActivity.getNewTheme() != R.style.Theme_Green) {
                        mainActivity.setNewTheme(R.style.Theme_Green)
                    }
                    disableLightAndDarkTheme()
                } else {
                    if (mainActivity.getNewTheme() != R.style.Theme_NasaPicture) {
                        mainActivity.setNewTheme(R.style.Theme_NasaPicture)
                    }
                    disableLightAndDarkTheme()
                }
            }

        }
    }

    private fun disableLightAndDarkTheme() {
        with(binding) {
            switchLightTheme.isChecked = false
            switchDarkTheme.isChecked = false
        }
    }

    private fun disableDarkAndGreenTheme() {
        with(binding) {
            switchDarkTheme.isChecked = false
            switchGreenTheme.isChecked = false
        }
    }

    private fun disableLightAndGreenTheme() {
        with(binding) {
            switchLightTheme.isChecked = false
            switchGreenTheme.isChecked = false
        }
    }
}