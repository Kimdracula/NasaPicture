package com.homework.nasapicture.ui.mars.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs
import kotlin.math.log

class FloatingButtonBehavior(
    context: Context,
    attrs: AttributeSet? = null
) : CoordinatorLayout.Behavior<View>(context, attrs) {


    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return (dependency is AppBarLayout)
    }


    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {

        val bar = dependency as AppBarLayout
        child.alpha = 1 - abs(1.1 * bar.y).toFloat() / bar.height.toFloat()
        child.x = bar.width * 0.2.toFloat() + bar.width - bar.height + bar.y * 2
        child.y = bar.height*0.8.toFloat() + bar.y
        Log.d("!!!", child.x.toString())
        Log.d("&&&", child.y.toString())
        Log.d("@@@", child.alpha.toString())




        return super.onDependentViewChanged(parent, child, dependency)
    }
}