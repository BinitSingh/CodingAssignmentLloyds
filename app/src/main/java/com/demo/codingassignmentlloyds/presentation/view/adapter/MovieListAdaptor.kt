package com.demo.codingassignmentlloyds.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.codingassignmentlloyds.databinding.ItemMovieBinding
import com.demo.codingassignmentlloyds.databinding.ItemMovieNewBinding
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.presentation.view.viewholder.MovieViewHolder
import javax.inject.Inject
import kotlin.properties.Delegates

typealias MovieClickListener = (Movie) -> Unit
class MovieListAdaptor @Inject constructor() : RecyclerView.Adapter<MovieViewHolder>() {

    lateinit var listner: MovieClickListener

    var itemList: List<Movie> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            parent.context, ItemMovieBinding.inflate(LayoutInflater.from(parent.context))
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = itemList[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            listner.invoke(movie)
        }
    }

    override fun getItemCount() = itemList.size


}