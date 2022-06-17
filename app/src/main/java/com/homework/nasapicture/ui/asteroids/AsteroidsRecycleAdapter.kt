package com.homework.nasapicture.ui.asteroids

import AsteroidsDTO
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.homework.nasapicture.R
import com.homework.nasapicture.databinding.RecycleAsteroidItemBinding

class AsteroidsRecycleAdapter(
    private val onItemListClickListener: OnItemListClickListener,
    private var asteroidsList: List<AsteroidsDTO> = emptyList()
) :
    RecyclerView.Adapter<AsteroidsRecycleAdapter.ViewHolder>() {


    fun setAsteroidsList(incomingList: List<AsteroidsDTO>) {
        asteroidsList = incomingList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecycleAsteroidItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asteroidsList[position])
    }

    override fun getItemCount(): Int = asteroidsList.size

    inner class ViewHolder(private val binding: RecycleAsteroidItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(asteroidsList: AsteroidsDTO) {
            with(binding) {
                textViewTitleAsteroid.text = asteroidsList.codename
                textViewCloseDateApproach.text =
                    asteroidsList.closeApproachDate
                if (asteroidsList.isPotentiallyHazardous) {
                    imgStatusHazardous.load(R.drawable.ic_status_danger)
                }
                root.setOnClickListener {
                    onItemListClickListener.onItemClick(asteroidsList)
                }
            }
        }
    }
}
