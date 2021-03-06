package com.homework.nasapicture.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.FragmentMainBinding
import com.homework.nasapicture.ui.settings.SettingsFragment
import com.homework.nasapicture.utils.UNKNOWN_ERROR
import com.homework.nasapicture.utils.WIKI_URL
import com.homework.nasapicture.viewmodel.MainState
import com.homework.nasapicture.viewmodel.MainViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>


    private val current: LocalDateTime = LocalDateTime.now()
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val formattedNow = current.format(formatter)


    companion object {
        fun newInstance() = MainFragment()
        private var isMain = true
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        setBottomAppBar(view)
        viewModel.sendRequest(formattedNow)
        setBottomSheetBehavior(binding.includeBottomSheet.bottomSheetContainer)
        setInputLayoutIconClickAction()
        setChipBehavior()
    }

    private fun setChipBehavior() {

        val yesterday = current.minusDays(1)
        val dby = current.minusDays(2)
        val formattedYesterday = yesterday.format(formatter)
        val formattedDby = dby.format(formatter)


        binding.tabsGroup.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        viewModel.sendRequest(formattedNow)
                    }
                    1 -> {
                        viewModel.sendRequest(formattedYesterday)
                    }
                    2 -> {
                        viewModel.sendRequest(formattedDby)
                    }
            }}

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })


    }
    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
    }


    private fun setInputLayoutIconClickAction() {
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("$WIKI_URL${binding.inputEditText.text.toString()}")
            })
        }
    }


    private fun renderData(it: MainState?) {
        when (it) {
            is MainState.Loading -> {
               binding.imageViewProgress.load(R.drawable.progress_animation)
            }
            is MainState.Error -> {
                with(binding) {
                imageViewProgress.visibility = View.GONE
                binding.nasaPictureImageView.load(R.drawable.error_image)}
            }
            is MainState.Success -> {
                with(binding) {
                    imageViewProgress.visibility = View.GONE
                    nasaPictureImageView.load(it.pictureOfTheDay.url)
                    includeBottomSheet.bottomSheetDescriptionHeader.text = it.pictureOfTheDay.title
                    includeBottomSheet.bottomSheetDescription.text = it.pictureOfTheDay.explanation
                }
            }

            else -> {
                MainState.Error(Throwable(UNKNOWN_ERROR))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_app_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> Toast.makeText(
                context, "Favourite",
                Toast.LENGTH_SHORT
            ).show()
            R.id.app_bar_settings ->
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SettingsFragment.newInstance())
                    .addToBackStack("")
                    .commit()



            android.R.id.home -> {
                BottomNavigationDrawerFragment.newInstance()
                    .show(requireActivity().supportFragmentManager, "")

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBottomAppBar(view: View) {
        val context = activity as MainActivity
        context.setSupportActionBar(view.findViewById(R.id.bottom_app_bar))
        setHasOptionsMenu(true)

        binding.fab.setOnClickListener {
            if (isMain) {
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.bottomAppBar.replaceMenu(R.menu.menu_app_bar_secondary)
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_back_fab
                    )
                )
            } else {
                binding.bottomAppBar.navigationIcon =
                    (ContextCompat.getDrawable(requireContext(), R.drawable.hamburger_icon))
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.bottomAppBar.replaceMenu(R.menu.menu_app_bar)
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.plus_fab_icon
                    )
                )
            }
            isMain = !isMain
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}