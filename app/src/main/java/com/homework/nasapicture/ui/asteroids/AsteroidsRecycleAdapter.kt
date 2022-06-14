package com.homework.nasapicture.ui.asteroids

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homework.nasapicture.databinding.RecycleAsteroidItemBinding
import com.homework.nasapicture.model.AsteroidsDTO

class AsteroidsRecycleAdapter(private val onItemListClickListener: OnItemListClickListener,
                              private var asteroidsList: List<AsteroidsDTO> = emptyList()
) :
    RecyclerView.Adapter<AsteroidsRecycleAdapter.ViewHolder>()  {


    fun setWeatherList(newWeatherList: List<Weather>) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecycleAsteroidItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int = weatherList.size

    inner class ViewHolder(private val binding: RecycleAsteroidItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Weather) {
            with(binding){
                textViewCityName.text = weather.city.name
                root.setOnClickListener {
                    onItemListClickListener.onItemClick(weather)
                }
            }
        }}
}