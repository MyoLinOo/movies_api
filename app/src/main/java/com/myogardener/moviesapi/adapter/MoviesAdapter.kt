package com.myogardener.moviesapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myogardener.moviesapi.R
import com.myogardener.moviesapi.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movies.view.*


class MoviesAdapter(var resultList: List<com.myogardener.moviesapi.model.Result> = ArrayList<com.myogardener.moviesapi.model.Result>()) :
    RecyclerView.Adapter<MoviesAdapter.HomeViewHolder>() {

    var mClickListener: ClickListener? = null

    fun setOnClickListener(clickListener: ClickListener){
        this.mClickListener = clickListener
    }


    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var result: Result

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(result: com.myogardener.moviesapi.model.Result) {
            this.result=result
            itemView.title.text = result.title

            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/"+result.poster_path)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.image)
            itemView.date.text= result.release_date
            itemView.overview.text= result.overview
        }

        override fun onClick(view: View?) {
            mClickListener?.onClcik(result)
        }
    }


    fun updateArticle(resultList: List<com.myogardener.moviesapi.model.Result>) {
        this.resultList = resultList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movies, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(resultList.get(position))
    }

    interface ClickListener {
        fun onClcik(result: com.myogardener.moviesapi.model.Result)
    }

}

