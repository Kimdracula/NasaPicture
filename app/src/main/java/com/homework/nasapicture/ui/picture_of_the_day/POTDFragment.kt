package com.homework.nasapicture.ui.picture_of_the_day

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

    var isImageClicked: Boolean = false
    private var _binding: FragmentPotdStartBinding? = null
    private val binding get() = _binding!!


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
    }

    private fun setWikiAnimation() {
        binding.wikiButton.setOnClickListener {
            val constraintSet = ConstraintSet()
            constraintSet.clone(binding.constraintContainer)

            val transition = ChangeBounds()
            transition.interpolator = AnticipateOvershootInterpolator(10f)
            transition.duration = 1000
            TransitionManager.beginDelayedTransition(binding.constraintContainer,transition )

            isImageClicked = !isImageClicked
            if(isImageClicked){
                constraintSet.connect(R.id.input_layout,
                    ConstraintSet.LEFT,R.id.constraint_container,
                    ConstraintSet.LEFT)
            }else{
                constraintSet.connect(R.id.input_layout,
                    ConstraintSet.LEFT,R.id.constraint_container,
                    ConstraintSet.RIGHT)
            }
            constraintSet.applyTo(binding.constraintContainer)

            binding.wikiButton.alpha=0f

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