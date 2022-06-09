package com.homework.nasapicture.ui.picture_of_the_day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.FragmentPotdBinding


class POTDFragment: Fragment() {

    private var _binding: FragmentPotdBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPotdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = ViewPagerAdapter(requireActivity())

        TabLayoutMediator(
            binding.tabsGroup, binding.viewPager
        ) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.today)
                1 -> getString(R.string.yesterday)
                else -> getString(R.string.tdby)
            }
        }.attach()


    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        fun newInstance() = POTDFragment()
    }
}