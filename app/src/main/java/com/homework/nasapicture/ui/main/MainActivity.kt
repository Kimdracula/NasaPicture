package com.homework.nasapicture.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.homework.nasapicture.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNowAllowingStateLoss()
        }
    }
}