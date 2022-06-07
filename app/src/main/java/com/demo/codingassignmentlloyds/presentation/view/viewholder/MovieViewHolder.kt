package com.demo.codingassignmentlloyds.presentation.view.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.demo.codingassignmentlloyds.R
import com.demo.codingassignmentlloyds.databinding.ItemMovieBinding
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.utility.loadImageOrDefault

class MovieViewHolder(
    private val context: Context,
    private val binding: ItemMovieBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        with(binding) {
            movie.image?.let { imgMovie.loadImageOrDefault(it) }
            txtName.text = movie.title
            movie.year?.let {
                txtYear.text = String.format(context.getString(R.string.year), it)
            }
            movie.crew?.let {
                txtCrew.text = String.format(context.getString(R.string.crew), it)
            }
            movie.imDbRating?.let {
                txtRating.text = String.format(context.getString(R.string.imdb_rating), it)
            }
        }
    }
}