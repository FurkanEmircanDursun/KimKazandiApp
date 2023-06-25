package com.example.kimkazandi.adapter


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide

import com.example.kimkazandi.model.Draw
import com.example.kimkazandi.R
import com.example.kimkazandi.model.Follow
import com.example.kimkazandi.roomFollow.FollowDao
import com.example.kimkazandi.roomFollow.FollowDatabase

private lateinit var db: FollowDatabase
private lateinit var followDao: FollowDao
class FollowAdapter(val followList: MutableList<Follow>, val context: Context) :
    RecyclerView.Adapter<FollowAdapter.FollowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent,
            false
        )
        return FollowViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        val news = followList[position]

        val draw = Draw(news.baseUrl,news.title,news.time,news.count,news.price,news.img,news.link)

        holder.titleTextView.text = news.title
        holder.timeTextView.text = news.time
        holder.tlTextView.text = news.count
        holder.priceTextView.text = news.price

        holder.itemView.setOnClickListener {

            val bundle = Bundle()
            bundle.putSerializable("data", draw)
            Navigation.findNavController(holder.itemView)
                .navigate(R.id.action_nav_myFollow_to_detailFragment, bundle)

        }


        Glide.with(context)
            .load("https://www.kimkazandi.com/" + news.img)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return followList.size
    }

    inner class FollowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.findViewById(R.id.itemImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.itemTitleTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.itemTimeTextView)
        val tlTextView: TextView = itemView.findViewById(R.id.itemTlTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.itemPriceTextView)
    }
}