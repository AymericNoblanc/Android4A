package com.example.android4a.presentation.listActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android4a.R
import com.example.android4a.data.remote.remote.Pages
import com.squareup.picasso.Picasso


class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.row_layout, parent, false)) {
    private var titre: TextView? = null
    private var coordonee: TextView? = null
    private var image: ImageView? = null

    init {
        titre = itemView.findViewById(R.id.firstLine)
        coordonee = itemView.findViewById(R.id.secondLine)
        image = itemView.findViewById(R.id.icon)
    }

    fun bind(pages: Pages) {
        titre?.text = pages.title
        coordonee?.text = "Latitude : " + pages.coordinates[0].lat + " - Longitude : " + pages.coordinates[0].lon

        if(pages.thumbnail != null){
            Picasso.get().load(
                    pages.thumbnail.source
            ).into(image)
        }

    }

}
