package com.homework.nasapicture.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Date {

  val current: LocalDateTime = LocalDateTime.now()
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formattedNow = current.format(formatter)
    val yesterday = current.minusDays(1)
    val dby = current.minusDays(2)
    val weekAgo = current.minusWeeks(1)
    val formattedWeekAgo = weekAgo.format(formatter)
    val formattedYesterday = yesterday.format(formatter)
    val formattedDby = dby.format(formatter)

}

