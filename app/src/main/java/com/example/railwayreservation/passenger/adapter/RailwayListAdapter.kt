package com.example.railwayreservation.passenger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.railwayreservation.R
import com.example.railwayreservation.passenger.models.CategoryModel
import java.util.*

class RailwayListAdapter(val categoryList: List<CategoryModel?>?, val clickListener: RailwayListClickListener): RecyclerView.Adapter<RailwayListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RailwayListAdapter.MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_railway_list_row, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RailwayListAdapter.MyViewHolder, position: Int) {
        holder.bind(categoryList?.get(position))
        holder.itemView.setOnClickListener {
            clickListener.onItemClick(categoryList?.get(position)!!)
        }
    }

    override fun getItemCount(): Int {
        return categoryList?.size!!
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var thumbImage: ImageView = view.findViewById(R.id.thumbImage)
        var tvType: TextView = view.findViewById(R.id.tvType)
        var tvCat: TextView = view.findViewById(R.id.tvCat)

        fun bind(categoryModel: CategoryModel?) {
            tvType.text = "Ticket Type: "+categoryModel?.type
            tvCat.text = "Ticket Category: "+categoryModel?.cat

            Glide.with(thumbImage)
                .load(categoryModel?.image)
                .into(thumbImage)
        }
    }

    interface RailwayListClickListener {
        fun onItemClick(categoryModel: CategoryModel)
    }

}