package com.homework.nasapicture.utils

import android.view.ViewGroup
import android.widget.ImageView
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet

class ImageScale {

    fun scale(image: ImageView, sceneRoot:ViewGroup){
        val transitionSet = TransitionSet()
        transitionSet.addTransition(ChangeBounds())
        transitionSet.addTransition(ChangeImageTransform())
        transitionSet.duration = 1000
        TransitionManager.beginDelayedTransition(sceneRoot, transitionSet)
    }
}