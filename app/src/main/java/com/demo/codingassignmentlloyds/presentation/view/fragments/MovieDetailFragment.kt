package com.demo.codingassignmentlloyds.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.demo.codingassignmentlloyds.databinding.FragmentMovieDetailBinding
import com.demo.codingassignmentlloyds.presentation.viewmodel.MovieDetailViewModel
import com.demo.codingassignmentlloyds.presentation.viewmodel.MovieListViewModel
import com.demo.codingassignmentlloyds.presentation.viewmodel.ViewState
import com.demo.codingassignmentlloyds.utility.Constant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    private val TAG = MovieDetailFragment::class.java.canonicalName
    private val viewModel: MovieDetailViewModel by viewModels()
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
        arguments?.getString(Constant.MOVIE_ID)?.let {
            viewModel.fetchMovieDetail()
        }?: run {
            Log.e(TAG,"Failed to load movie")
        }

        lifecycleScope.launchWhenStarted {
            viewModel.getViewStateFlow().collect { viewState ->
                when (viewState) {
                    is ViewState.Loading -> {
                        binding.progressBar.visibility =
                            if (viewState.isLoading) View.VISIBLE else View.GONE
                    }
                    is ViewState.Failure -> {
                        viewState.throwable.message?.let {
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        }
                    }
                    is ViewState.Success -> {
                        val data = viewState.output
                        Log.v(TAG, data.toString())
                    }
                }
            }
        }
    }

}