package com.myogardener.moviesapi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.myogardener.moviesapi.R
import com.myogardener.moviesapi.adapter.MoviesAdapter
import com.myogardener.moviesapi.model.Result
import com.myogardener.moviesapi.viewmodel.ViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), MoviesAdapter.ClickListener {

    lateinit var moviesViewModel: ViewModel
    lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesViewModel = ViewModelProvider(this)
            .get(ViewModel::class.java)

        moviesAdapter = MoviesAdapter()
        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moviesAdapter
        }
        moviesAdapter.setOnClickListener(this)
        observeViewmodel()
    }

    private fun observeViewmodel() {
        moviesViewModel.loadNews()
        moviesViewModel.getResult().observe(
            viewLifecycleOwner, Observer {movie->
                moviesAdapter.updateArticle(movie.results)
            }
        )
    }

    override fun onResume() {
        super.onResume()
        moviesViewModel.loadNews()
    }

    override fun onClcik(result: Result) {
        var action = HomeFragmentDirections.actionHomeFragmentToSecondFragment(result.id.toString())
        findNavController().navigate(action)
    }


}




