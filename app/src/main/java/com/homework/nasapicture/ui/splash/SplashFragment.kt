package com.homework.nasapicture.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.FragmentSplashBinding
import com.homework.nasapicture.ui.main.ApiBottomFragment


class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val handler = Handler(Looper.getMainLooper())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.cometRed
            .animate()
            .scaleX(5f)
            .scaleY(5f)
            .translationX(1020f)
            .translationY(1400f)
            .alpha(0f)
            .setStartDelay(1000)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(4000).start()

        binding.comet.animate().translationX(-1720f)

            .translationY(1800f)
            .setStartDelay(5000)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(5000)
            .setListener(object : Animator.AnimatorListener{
                override fun onAnimationStart(p0: Animator?) {
                }

                override fun onAnimationEnd(p0: Animator?) {
                    parentFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            com.google.android.material.R.anim.mtrl_bottom_sheet_slide_in,
                            com.google.android.material.R.anim.mtrl_bottom_sheet_slide_out)
                        .replace(R.id.container, ApiBottomFragment.newInstance())
                        .commitNowAllowingStateLoss()
                }

                override fun onAnimationCancel(p0: Animator?) {
                }

                override fun onAnimationRepeat(p0: Animator?) {
                }
            })
            .start()


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        handler.removeCallbacksAndMessages(null)
    }

    companion object {

        fun newInstance() =
            SplashFragment()
    }
}
