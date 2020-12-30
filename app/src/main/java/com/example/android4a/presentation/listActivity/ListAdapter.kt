package com.example.android4a.presentation.listActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android4a.R
import com.example.android4a.data.remote.remote.Pages

class ListAdapter(private val list: List<Pages>)
    : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val pages: Pages = list[position]
        holder.bind(pages)
    }

    override fun getItemCount(): Int = list.size

}