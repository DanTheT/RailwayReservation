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
import java.text.SimpleDateFormat
import java.util.*

class RestaurantListAdapter(val categoryList: List<CategoryModel?>?, val clickListener: RestaurantListClickListener): RecyclerView.Adapter<RestaurantListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantListAdapter.MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_restautant_list_row, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantListAdapter.MyViewHolder, position: Int) {
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
        val tvRestaurantName: TextView = view.findViewById(R.id.tvRestaurantName)
        val tvRestaurantAddress: TextView = view.findViewById(R.id.tvRestaurantAddress)
        val tvRestaurantHours: TextView = view.findViewById(R.id.tvRestaurantHours)

        fun bind(restaurentModel: CategoryModel?) {
            tvRestaurantName.text = "Ticket Type: "+restaurentModel?.name
            tvRestaurantAddress.text = "Ticket Category: "+restaurentModel?.address
            tvRestaurantHours.text = " " + restaurentModel?.time

            Glide.with(thumbImage)
                .load(restaurentModel?.image)
                .into(thumbImage)
        }
    }

    interface RestaurantListClickListener {
        fun onItemClick(categoryModel: CategoryModel)
    }

}