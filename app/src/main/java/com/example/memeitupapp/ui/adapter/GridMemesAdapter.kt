package com.example.memeitupapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.memeitupapp.R
import com.globant.domain.entity.Meme
import kotlinx.android.synthetic.main.layout_meme_grid_element.view.grid_memes_element_image_view

class GridMemesAdapter(private val onMemeClicked: (Meme) -> Unit) : RecyclerView.Adapter<GridMemesAdapter.ViewHolder>() {

    private val memes = mutableListOf<Meme>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_meme_grid_element,
                parent,
                false
            ), onMemeClicked
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(memes[position])
    }

    override fun getItemCount(): Int = memes.size

    fun submitList(memesList: List<Meme>) {
        memes.addAll(memesList)
    }

    class ViewHolder(itemView: View, private val onMemeClicked: (Meme) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(item: Meme) = with(itemView) {
            setOnClickListener { onMemeClicked(item) }
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.imagenotavailable)
                .error(R.drawable.imagenotavailable)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(item.image)
                .into(grid_memes_element_image_view)
        }
    }
}