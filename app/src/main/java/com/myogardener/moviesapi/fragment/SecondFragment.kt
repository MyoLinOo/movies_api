package com.myogardener.moviesapi.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.myogardener.moviesapi.R
import com.myogardener.moviesapi.model.Detail
import com.myogardener.moviesapi.viewmodel.ViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : Fragment() {
    lateinit var detailviewModel: ViewModel
    lateinit var detail: Detail

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailviewModel = ViewModelProvider(this)
            .get(ViewModel::class.java)
        var messageArgs = arguments?.let {
            SecondFragmentArgs.fromBundle(it)
        }
        var id:String=messageArgs!!.url
        Log.d("id", id)
        onviewModel(id)


        // webView.loadUrl(url)

    }

    private fun onviewModel(movieid:String){


        detailviewModel.loadNews2(movieid)
        detailviewModel.getResult2().observe(
            viewLifecycleOwner, Observer {movies->
                Log.d("moviedetail",movies.toString())
                detail_overview.text=movies.overview

                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500/"+movies.poster_path)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(detail_image)
                title_detail.text = movies.title


            }
        )
    }
//    fun bind(detail: Detail) {
//        this.detail=detail
//        //           itemView.articleTitle.text = article.title
//
//        Picasso.get()
//            .load("https://image.tmdb.org/t/p/w500/"+detail.poster_path)
//            .placeholder(R.drawable.ic_launcher_background)
//            .into(detail_image)
//    }

}