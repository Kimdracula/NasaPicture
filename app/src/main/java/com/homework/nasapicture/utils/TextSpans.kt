package com.homework.nasapicture.utils

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.QuoteSpan
import android.text.style.TypefaceSpan
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.homework.nasapicture.R

class TextSpans {

    fun setQuote(string: String):SpannableString{
        val spannable = SpannableString(string)
        spannable.setSpan(QuoteSpan(Color.YELLOW,
            20, 40),0,spannable.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
   return spannable
    }

    fun underline (string: String, context: Context):SpannableString{
        val spannable = SpannableString(string)
        spannable.setSpan(UnderlineSpan(), 0,spannable.length,Spannable.SPAN_EXCLUSIVE_INCLUSIVE )
        spannable.setSpan(ResourcesCompat.getFont(context, R.font.rubik_mono_one)
            ?.let { TypefaceSpan(it) },0,spannable.length,Spannable.SPAN_EXCLUSIVE_INCLUSIVE )
        return spannable
    }

    fun makeRainbow(string: String): SpannableString {
        val spannable = SpannableString(string)

        for (i in 0..(spannable.length - 6)) {
            if (i % 7 == 0) {

                spannable.setSpan(
                    ForegroundColorSpan(Color.RED),
                    i, i + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )

                spannable.setSpan(
                    ForegroundColorSpan(Color.rgb(255, 165, 0)),
                    i + 1, i + 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )

                spannable.setSpan(
                    ForegroundColorSpan(Color.YELLOW),
                    i + 2, i + 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )

                spannable.setSpan(
                    ForegroundColorSpan(Color.GREEN),
                    i + 3, i + 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )


                spannable.setSpan(
                    ForegroundColorSpan(Color.rgb(0, 191, 255)),
                    i + 4, i + 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )


                spannable.setSpan(
                    ForegroundColorSpan(Color.BLUE),
                    i + 5, i + 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )

                spannable.setSpan(
                    ForegroundColorSpan(Color.rgb(139, 0, 255)),
                    i + 6, i + 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )
            }
        }
        return spannable
    }
}