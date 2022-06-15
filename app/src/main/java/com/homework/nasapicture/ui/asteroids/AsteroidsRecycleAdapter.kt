package com.homework.nasapicture.ui.asteroids

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homework.nasapicture.databinding.RecycleAsteroidItemBinding
import com.homework.nasapicture.model.X20150907

class AsteroidsRecycleAdapter(
                              private var asteroidsList: List<X20150907> = emptyList()
) :
    RecyclerView.Adapter<AsteroidsRecycleAdapter.ViewHolder>()  {
fun setAsteroidsList(incomingList: List<X20150907>) {
    asteroidsList = incomingList
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecycleAsteroidItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asteroidsList[position])
    }

    override fun getItemCount(): Int = asteroidsList.size

    inner class ViewHolder(private val binding: RecycleAsteroidItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(asteroidsList: X20150907) {
            with(binding){
                textViewTitleAsteroid.text = asteroidsList.name
               textViewCloseDateApproach.text = asteroidsList.closeApproachData[0].closeApproachDate

                }
            }
        }}
