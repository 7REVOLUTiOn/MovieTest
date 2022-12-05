package com.example.movietest.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movietest.R
import com.example.movietest.domain.entity.MovieBean
import com.squareup.picasso.Picasso


class RecyclerViewAdapter(
    private val rezult:
    List<MovieBean.Search?>
) :
    RecyclerView.Adapter<RecyclerViewAdapter.MovieItemViewHolder>() {

//    var movieList = listOf<MovieBean.Search>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    var movieList = rezult


    var onMovieItemClickListener: ((MovieBean.Search?) ->  Unit)? = null

    //val list = listOf<MovieBean.Search>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        val holder = MovieItemViewHolder(view)
        holder.view.setOnClickListener{
            onMovieItemClickListener?.invoke(movieList[holder.adapterPosition])
        }
        return holder

    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val movieBean = movieList[position]
        if (movieBean != null) {
            holder.title.text = movieBean.title
            holder.year.text = movieBean.year
            holder.genre.text = movieBean.type
            Picasso.get().load(movieBean.poster).fit().centerInside().into(holder.imageView)
        }

        Log.d("Picasso", "${movieBean?.poster}")



            /*Log.d("click lisner", "open new fragment")//Клик по элементу recyclerView
            //Отсюда буду запускать фрагмент
            val id = movieBean?.imdbID //Получаем id через
            Log.d("click lisner", "${id}")

            //findNavController(holder.view).navigate(R.id.action_recyclerViewFragmentFragment2_to_movieFromRecyclerView)
            findNavController(holder.view).navigate(
                RecyclerViewFragmentDirections.actionRecyclerViewFragmentFragment2ToMovieFromRecyclerView(
                    movieBean?.imdbID.toString()
                )
            )*/

//        holder.view.setOnClickListener(){
//            onShopItemClickListener?.invoke(holder.view.id)
//        }
    }


    override fun getItemCount(): Int {
        return movieList.size
    }

    class MovieItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.titleTextView)
        val year = view.findViewById<TextView>(R.id.yearTextView)
        val imageView = view.findViewById<ImageView>(R.id.posterImageView)
        val genre = view.findViewById<TextView>(R.id.genre)

    }



}