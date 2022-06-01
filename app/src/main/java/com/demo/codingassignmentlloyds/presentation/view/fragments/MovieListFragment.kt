package com.demo.codingassignmentlloyds.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.demo.codingassignmentlloyds.R
import com.demo.codingassignmentlloyds.databinding.FragmentMovieListBinding
import com.demo.codingassignmentlloyds.presentation.view.adapter.MovieClickListener
import com.demo.codingassignmentlloyds.presentation.view.adapter.MovieListAdaptor
import com.demo.codingassignmentlloyds.presentation.viewmodel.MovieListViewModel
import com.demo.codingassignmentlloyds.presentation.viewmodel.ViewState
import com.demo.codingassignmentlloyds.utility.Constant
import com.demo.codingassignmentlloyds.utility.ItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentMovieListBinding
    private val viewModel: MovieListViewModel by viewModels()

    @Inject
    lateinit var movieAdaptor: MovieListAdaptor

    private val topArtistItemClickListener: MovieClickListener = { movie ->
        findNavController().navigate(
            R.id.action_show_moviedetail,
            Bundle().apply {
                putParcelable(Constant.MOVIE, movie)
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            rvMovie.addItemDecoration(
                ItemDecoration(resources.getDimension(R.dimen.dimen_16dp).toInt())
            )
//            viewModel.getMovieLiveData().observe(viewLifecycleOwner) { viewState ->
//                when (viewState) {
//                    is ViewState.Loading -> {
//                        progressBar.visibility =
//                                if (viewState.isLoading) View.VISIBLE else View.GONE
//                    }
//                    is ViewState.Failure -> {
//                        viewState.throwable.message?.let {
//                                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
//                            }
//                    }
//                    is ViewState.Success -> {
//                        val data = viewState.output
//                        with(movieAdaptor){
//                            itemList = data.subList(0,5)
//                            rvMovie.adapter = this
//                            listner = topArtistItemClickListener
//                        }
//                    }
//                }
//            }

            lifecycleScope.launchWhenStarted {
                viewModel.getViewStateFlow().collect { viewState ->
                    when (viewState) {
                        is ViewState.Loading -> {
                            progressBar.visibility =
                                if (viewState.isLoading) View.VISIBLE else View.GONE
                        }
                        is ViewState.Failure -> {
                            viewState.throwable.message?.let {
                                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                            }
                        }
                        is ViewState.Success -> {
                            val data = viewState.output
                            with(movieAdaptor){
                                rvMovie.adapter = this
                                listner = topArtistItemClickListener
                                itemList = data
                            }
                        }
                    }
                }
            }
        }
        viewModel.fetchMovieList()
    }

}