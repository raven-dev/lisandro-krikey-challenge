package com.lisandrolopez.krikeychallenge.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.lisandrolopez.krikeychallenge.R
import com.lisandrolopez.krikeychallenge.repository.model.Cat
import com.lisandrolopez.krikeychallenge.util.GlideApp

class CatListAdapter(private var catList: List<Cat>,
                     private val callback: OnCatSelectedListener): RecyclerView.Adapter<CatListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = catList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cat = catList[position]
        holder.ivCat.transitionName = cat.url
        GlideApp.with(holder.itemView)
            .load(cat.url)
            .centerCrop()
            .into(holder.ivCat)

        holder.container.setOnClickListener {
            callback.onCatSelected(holder.ivCat, cat)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivCat: ImageView = itemView.findViewById(R.id.iv_cat)
        val container: View = itemView.findViewById(R.id.cv_cat)
    }

    interface OnCatSelectedListener {
        fun onCatSelected(imageView: ImageView, cat: Cat)
    }
}
