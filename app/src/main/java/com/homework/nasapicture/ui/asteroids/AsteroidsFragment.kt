package com.homework.nasapicture.ui.asteroids

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.FragmentAsteroidsBinding
import com.homework.nasapicture.model.X20150907
import com.homework.nasapicture.utils.ASTEROIDS_KEY_BUNDLE
import com.homework.nasapicture.utils.Date
import com.homework.nasapicture.utils.SERVER_ERROR
import com.homework.nasapicture.viewmodel.AsteroidsState
import com.homework.nasapicture.viewmodel.AsteroidsViewModel


class AsteroidsFragment : Fragment(), OnItemListClickListener {

    private var _binding: FragmentAsteroidsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AsteroidsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAsteroidsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AsteroidsViewModel::class.java]
        val date = Date()
        viewModel.sendRequest(date.formattedNow)
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        initDecorator()

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initDecorator() {
        val itemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(resources.getDrawable(R.drawable.separator, null))
        binding.recycleList.addItemDecoration(itemDecoration)
    }

    private fun renderData(it: AsteroidsState?) {
        when (it) {
            is AsteroidsState.Loading -> {
                binding.imageViewProgress.load(R.drawable.progress_animation)
            }
            is AsteroidsState.Error -> {
                with(binding) {
                    imageViewProgress.visibility = View.GONE
                    Toast.makeText(requireContext(), SERVER_ERROR, Toast.LENGTH_LONG)
                        .show()
                }
            }
            is AsteroidsState.Success -> {
                binding.imageViewProgress.visibility = View.GONE
                val myAdapter = AsteroidsRecycleAdapter(this)
                myAdapter.setAsteroidsList(it.asteroids.nearEarthObjects.x20150907)
                binding.recycleList.adapter = myAdapter
            }
            else -> {
                Toast.makeText(requireContext(), SERVER_ERROR, Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        fun newInstance() = AsteroidsFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(asteroidsList: X20150907) {
      requireActivity().supportFragmentManager.beginTransaction().replace(R.id.containerApi, AsteroidsDetailsFragment.newInstance(
           Bundle().apply {
               putParcelable(ASTEROIDS_KEY_BUNDLE,asteroidsList)
    })).commitNowAllowingStateLoss()
}
}