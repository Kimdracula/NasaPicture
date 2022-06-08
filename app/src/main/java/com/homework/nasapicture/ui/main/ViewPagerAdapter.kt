package com.homework.nasapicture.ui.main


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.homework.nasapicture.utils.DBY_FRAGMENT
import com.homework.nasapicture.utils.TODAY_FRAGMENT
import com.homework.nasapicture.utils.YESTERDAY_FRAGMENT

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments = arrayOf(
        TodayFragment.newInstance(),
        TDBYFragment.newInstance(),
        YesterdayFragment.newInstance()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
       return when (position) {
            0 -> fragments[TODAY_FRAGMENT]
            1 -> fragments[YESTERDAY_FRAGMENT]
            2 -> fragments[DBY_FRAGMENT]
            else -> fragments[TODAY_FRAGMENT]
        }
    }

}