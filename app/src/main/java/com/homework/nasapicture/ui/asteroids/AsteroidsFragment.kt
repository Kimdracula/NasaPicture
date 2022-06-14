package com.homework.nasapicture.ui.asteroids

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.homework.nasapicture.databinding.FragmentAsteroidsBinding
import com.homework.nasapicture.utils.Date
import com.homework.nasapicture.viewmodel.AsteroidsState
import com.homework.nasapicture.viewmodel.AsteroidsViewModel


class AsteroidsFragment : Fragment() {

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
    }

    private fun renderData(it: AsteroidsState?) {
when(it){
    is AsteroidsState.Success ->{
        val adapter = AsteroidsRecycleAdapter(it.asteroids.nearEarthObjects.x20150907)
        binding.recycleList.adapter =adapter
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

    }