package com.demo.codingassignmentlloyds.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.demo.codingassignmentlloyds.databinding.FragmentMovieDetailBinding
import com.demo.codingassignmentlloyds.domain.datamodel.Movie
import com.demo.codingassignmentlloyds.presentation.viewmodel.ViewState
import com.demo.codingassignmentlloyds.utility.Constant
import com.demo.codingassignmentlloyds.utility.loadImageOrDefault
import kotlinx.coroutines.flow.collect

class MovieDetailFragment : Fragment() {
    private val TAG = MovieDetailFragment::class.java.canonicalName
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Movie>(Constant.MOVIE)?.let {
            renderUi(it)
        }?: run {
            Log.e(TAG,"Failed to load movie")
        }
    }

    private fun renderUi(movie: Movie) {
        with(binding) {
            movie.image?.let {
                imgBanner.loadImageOrDefault(it)
            }
            titleVal.text = movie.title ?: ""
            crewVal.text = movie.crew ?: ""
            ratingVal.text = movie.imDbRating ?: ""
            ratingCountVal.text = movie.imDbRatingCount ?: ""
            yearLabelVal.text = movie.year ?: ""
        }
    }
}