package com.example.kimkazandi.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide

import com.example.kimkazandi.model.Draw
import com.example.kimkazandi.R
import com.example.kimkazandi.detail.DetailFragment
import com.example.kimkazandi.model.Follow
import com.example.kimkazandi.roomFollow.FollowDao
import com.example.kimkazandi.roomFollow.FollowDatabase

private lateinit var db: FollowDatabase
private lateinit var followDao: FollowDao
class DrawAdapter(private val drawList: MutableList<Draw>, private val context: Context,val fragment:String) :
    RecyclerView.Adapter<DrawAdapter.DrawViewHolder>() {

    private lateinit var fragmentManager: FragmentManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent,
            false
        )
        fragmentManager = (context as FragmentActivity).supportFragmentManager
        return DrawViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DrawViewHolder, position: Int) {
        val news = drawList[position]

        holder.titleTextView.text = news.title
        holder.timeTextView.text = news.time
        holder.tlTextView.text = news.count
        holder.priceTextView.text = news.price

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("data", news)
            when (fragment) {
                "GeneralDrawFragment" -> Navigation.findNavController(holder.itemView)
                    .navigate(R.id.action_nav_general_to_detailFragment, bundle)

                "FreeDrawFragment" -> Navigation.findNavController(holder.itemView)
                    .navigate(R.id.action_nav_free_to_detailFragment, bundle)

                "CarDrawFragment" -> Navigation.findNavController(holder.itemView)
                    .navigate(R.id.action_nav_car_to_detailFragment, bundle)

                "VacationDrawFragment" -> Navigation.findNavController(holder.itemView)
                    .navigate(R.id.action_nav_vacation_to_detailFragment, bundle)

                "PhoneAndTabletFragment" -> Navigation.findNavController(holder.itemView)
                    .navigate(R.id.action_nav_phoneAndTablet_to_detailFragment, bundle)

                "BeginsFragment" -> Navigation.findNavController(holder.itemView)
                    .navigate(R.id.action_nav_begins_to_detailFragment, bundle)
            }
        }

        Glide.with(context)
            .load("https://www.kimkazandi.com/" + news.img)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return drawList.size
    }

    inner class DrawViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.itemImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.itemTitleTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.itemTimeTextView)
        val tlTextView: TextView = itemView.findViewById(R.id.itemTlTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.itemPriceTextView)
    }
}