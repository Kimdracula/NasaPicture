package com.homework.nasapicture.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.FragmentApiBottomBinding
import com.homework.nasapicture.ui.mars.MarsFragment
import com.homework.nasapicture.ui.asteroids.AsteroidsFragment
import com.homework.nasapicture.ui.earth.EarthFragment
import com.homework.nasapicture.ui.picture_of_the_day.POTDFragment
import com.homework.nasapicture.ui.settings.SettingsFragment


class ApiBottomFragment : Fragment() {

    private var _binding: FragmentApiBottomBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApiBottomBinding.inflate(inflater, container, false)
        childFragmentManager.beginTransaction()
            .replace(R.id.containerApi, POTDFragment.newInstance()).commitNowAllowingStateLoss()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigationApi.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.action_picture_of_the_day -> {
                    childFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            com.google.android.material.R.anim.mtrl_bottom_sheet_slide_in,
                            com.google.android.material.R.anim.mtrl_bottom_sheet_slide_out)
                        .replace(R.id.containerApi, POTDFragment.newInstance()).commit()
                    true
                }
                R.id.action_mars -> {
                    childFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            com.google.android.material.R.anim.mtrl_bottom_sheet_slide_in,
                            com.google.android.material.R.anim.mtrl_bottom_sheet_slide_out)
                        .replace(R.id.containerApi, MarsFragment.newInstance()).commit()
                    true
                }
                R.id.action_asteroids -> {
                    childFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            com.google.android.material.R.anim.mtrl_bottom_sheet_slide_in,
                            com.google.android.material.R.anim.mtrl_bottom_sheet_slide_out)
                        .replace(R.id.containerApi, AsteroidsFragment.newInstance()).commit()
                    true
                }
                R.id.action_earth -> {
                    childFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            com.google.android.material.R.anim.mtrl_bottom_sheet_slide_in,
                            com.google.android.material.R.anim.mtrl_bottom_sheet_slide_out)
                        .replace(R.id.containerApi, EarthFragment.newInstance()).commit()
                    true
                }

                R.id.action_settings -> {
                    childFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            com.google.android.material.R.anim.mtrl_bottom_sheet_slide_in,
                            com.google.android.material.R.anim.mtrl_bottom_sheet_slide_out)
                        .replace(R.id.containerApi, SettingsFragment.newInstance()).commit()
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    companion object {
        fun newInstance() = ApiBottomFragment()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}