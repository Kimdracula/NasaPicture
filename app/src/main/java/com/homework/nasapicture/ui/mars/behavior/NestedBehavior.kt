package com.homework.nasapicture.ui.mars.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

class NestedBehavior (
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
            child.y = bar.height.toFloat() + bar.y/2

            return super.onDependentViewChanged(parent, child, dependency)
        }
}