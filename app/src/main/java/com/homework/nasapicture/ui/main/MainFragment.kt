package com.homework.nasapicture.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.FragmentMainBinding
import com.homework.nasapicture.utils.UNKNOWN_ERROR
import com.homework.nasapicture.utils.WIKI_URL
import com.homework.nasapicture.viewmodel.MainState
import com.homework.nasapicture.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>


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
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        setBottomAppBar(view)
        viewModel.getPictures()
        setBottomSheetBehavior(binding.includeBottomSheet.bottomSheetContainer)
        setInputLayoutIconClickAction()
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
                binding.progressIndicator.visibility = View.VISIBLE
            }
            is MainState.Error -> {}
            is MainState.Success -> {
                with(binding) {
                    progressIndicator.visibility = View.GONE
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
            R.id.app_bar_fav -> Toast.makeText(context, "Favourite",
                Toast.LENGTH_SHORT).show()
            R.id.app_bar_search -> Toast.makeText(context, "Search",
                Toast.LENGTH_SHORT).show()
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
            if(isMain){
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_back_fab))
                // TODO HW  binding.bottomAppBar.replaceMenu(// R.menu. какое-то другое меню)
            }else{
                binding.bottomAppBar.navigationIcon = (ContextCompat.getDrawable(requireContext(),R.drawable.hamburger_icon))
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.plus_fab_icon))
                // TODO HW binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
            isMain = !isMain
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}