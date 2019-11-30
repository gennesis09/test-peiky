package com.example.testpeiky.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testpeiky.databinding.MovieItemBinding
import com.example.testpeiky.models.Movies
import com.squareup.picasso.Picasso

class MoviesAdapter(context: Context): RecyclerView.Adapter<MoviesAdapter.MovieItem>() {
    private val list: MutableList<Movies> = mutableListOf()
    private val picasso: Picasso by lazy {
        Picasso.Builder(context).build()
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieItem, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItem {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MovieItem(binding)
    }

    fun updateItems(listPortfolio: List<Movies>) {
        list.addAll(listPortfolio)
        notifyDataSetChanged()
    }

    inner class MovieItem(
        val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("Range")
        fun bind(item: Movies) {
            binding.movie = item
            picasso
                .load("${IMAGE_BASE_URL}${item.posterPath}")
                .into(binding.poster)
        }
    }

    companion object {
        const val IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185"
    }
}