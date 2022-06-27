package com.homework.nasapicture.ui.picture_of_the_day

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.google.android.material.tabs.TabLayoutMediator
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.FragmentPotdStartBinding
import com.homework.nasapicture.utils.WIKI_URL


class POTDFragment : Fragment() {

    private var isFabClicked: Boolean = false
    private var isImageClicked: Boolean = false
    private var _binding: FragmentPotdStartBinding? = null
    private val binding get() = _binding!!
    private val duration = 1000L


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPotdStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = ViewPagerAdapter(requireActivity())
        attachTabLayoutMediator()
        setInputLayoutIconClickAction()
        setWikiAnimation()
        setFabBehavior()
    }

    private fun setFabBehavior() {
        binding.fab.setOnClickListener {
            isFabClicked = !isFabClicked
            if (isFabClicked) {
                with(binding) {
                    ObjectAnimator.ofFloat(plusImageview, View.ROTATION, 0f, 315f)
                        .setDuration(duration).start()
                    ObjectAnimator.ofFloat(
                        optionOneContainer,
                        View.TRANSLATION_Y,
                        -50f,
                        -360f
                    )
                        .setDuration(duration).start()
                    ObjectAnimator.ofFloat(
                        optionTwoContainer,
                        View.TRANSLATION_Y,
                        -20f,
                        -230f
                    )
                        .setDuration(duration).start()

                    optionOneContainer.animate()
                        .alpha(1f)
                        .setDuration(duration / 2)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                optionOneContainer.isClickable = true
                            }
                        })
                    optionTwoContainer.animate()
                        .alpha(1f)
                        .setDuration(duration / 2)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                optionTwoContainer.isClickable = true
                            }
                        })
                    fadeBackground(0.5f)

                }
            } else {
                with(binding) {

                    ObjectAnimator.ofFloat(plusImageview, View.ROTATION, 315f, 0f)
                        .setDuration(duration).start()
                    ObjectAnimator.ofFloat(optionOneContainer, View.TRANSLATION_Y, -360f, -50f)
                        .setDuration(duration).start()
                    ObjectAnimator.ofFloat(optionTwoContainer, View.TRANSLATION_Y, -230f, -20f)
                        .setDuration(duration).start()

                    optionOneContainer.animate()
                        .alpha(0f)
                        .setDuration(duration / 2)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                optionOneContainer.isClickable = false
                            }
                        })
                    optionTwoContainer.animate()
                        .alpha(0f)
                        .setDuration(duration / 2)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                optionTwoContainer.isClickable = false
                            }
                        })
                    fadeBackground(1f)
                }
            }
        }
    }

    private fun fadeBackground(index: Float) {
        with(binding) {
            constraintContainer.animate()
                .alpha(index).duration = duration
            viewPager.animate()
                .alpha(index).duration = duration
        }
    }

    private fun setWikiAnimation() {
        binding.wikiButton.setOnClickListener {
            with(binding) {
                val constraintSet = ConstraintSet()
                constraintSet.clone(constraintContainer)

                val transition = ChangeBounds()
                transition.interpolator = AnticipateOvershootInterpolator(10f)
                transition.duration = 1000
                TransitionManager.beginDelayedTransition(constraintContainer, transition)

                isImageClicked = !isImageClicked
                if (isImageClicked) {
                    constraintSet.connect(
                        R.id.input_layout,
                        ConstraintSet.LEFT, R.id.constraint_container,
                        ConstraintSet.LEFT
                    )
                } else {
                    constraintSet.connect(
                        R.id.input_layout,
                        ConstraintSet.LEFT, R.id.constraint_container,
                        ConstraintSet.RIGHT
                    )
                }
                constraintSet.applyTo(constraintContainer)

                wikiButton.animate()
                    .alpha(0f)
                    .duration = 1000
            }
        }
    }

    private fun attachTabLayoutMediator() {
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

    private fun setInputLayoutIconClickAction() {
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("$WIKI_URL${binding.inputEditText.text.toString()}")
            })
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        fun newInstance() = POTDFragment()
    }
}