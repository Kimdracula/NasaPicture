package com.homework.nasapicture.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.homework.nasapicture.R
import com.homework.nasapicture.utils.KEY_NEW_THEME
import com.homework.nasapicture.utils.KEY_SP

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getNewTheme())
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ApiBottomFragment.newInstance())
                .commitNowAllowingStateLoss()
        }
    }

    fun getNewTheme(): Int {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_NEW_THEME, -1)
    }

    fun setNewTheme(currentTheme: Int) {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_NEW_THEME, currentTheme)
        editor.apply()
        recreate()
    }
}