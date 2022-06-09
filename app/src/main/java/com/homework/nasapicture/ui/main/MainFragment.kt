package com.homework.nasapicture.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.FragmentMainBinding
import com.homework.nasapicture.ui.mars.MarsFragment
import com.homework.nasapicture.ui.meteors.MeteorsFragment
import com.homework.nasapicture.ui.moon.MoonFragment
import com.homework.nasapicture.ui.picture_of_the_day.ViewPagerAdapter
import com.homework.nasapicture.ui.settings.SettingsFragment


class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
binding.viewPager.adapter = ViewPagerAdapter(requireActivity())

        TabLayoutMediator(binding.tabsGroup,binding.viewPager
        ) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.today)
                1 -> getString(R.string.yesterday)
                else -> getString(R.string.tdby)
            }
        }.attach()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.action_picture_of_the_day -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MainFragment.newInstance()).commit()
                    true
                }
                R.id.action_mars -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MarsFragment.newInstance()).commit()
                    true
                }
                R.id.action_meteors-> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MeteorsFragment.newInstance()).commit()
                    true                }
                R.id.action_moon -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MoonFragment.newInstance()).commit()
                    true
                }

                R.id.action_settings -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SettingsFragment.newInstance()).commit()
                    true
                }
                else -> {
                    true
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        fun newInstance() = MainFragment()
    }
}