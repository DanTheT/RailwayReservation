package com.example.railwayreservation.passenger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.passenger.TicketMenuActivity
import com.example.railwayreservation.passenger.models.Category

class CategoryListAdapter(val menuList: List<Category?>?, val clickListener: TicketMenuActivity): RecyclerView.Adapter<CategoryListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryListAdapter.MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.menu_list_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryListAdapter.MyViewHolder, position: Int) {
        holder.bind(menuList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if(menuList == null)return 0 else menuList.size
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var thumbImage: ImageView = view.findViewById(R.id.thumbImage)
        val menuName: TextView = view.findViewById(R.id.menuName)
        val menuPrice: TextView = view.findViewById(R.id.menuPrice)
        val addToCartButton: TextView = view.findViewById(R.id.addToCartButton)
        val addMoreLayout: LinearLayout = view.findViewById(R.id.addMoreLayout)
        val imageMinus: ImageView = view.findViewById(R.id.imageMinus)
        val imageAddOne: ImageView = view.findViewById(R.id.imageAddOne)
        val tvCount: TextView = view.findViewById(R.id.tvCount)

        fun bind(category: Category) {
            menuName.text = category?.name
            menuPrice.text = "Price: RM ${category?.price}"
            addToCartButton.setOnClickListener {
                category?.totalInCart = 1
                clickListener.addToCartClickListener(category)
                addMoreLayout?.visibility = View.VISIBLE
                addToCartButton.visibility = View.GONE
                tvCount.text = category?.totalInCart.toString()
            }
            imageMinus.setOnClickListener {
                var total: Int =  category?.totalInCart
                total--
                if(total > 0) {
                    category?.totalInCart = total
                    clickListener.updateCartClickListener(category)
                    tvCount.text = category?.totalInCart.toString()
                } else {
                    category.totalInCart = total
                    clickListener.removeFromCartClickListener(category)
                    addMoreLayout.visibility = View.GONE
                    addToCartButton.visibility = View.VISIBLE
                }
            }
            imageAddOne.setOnClickListener {
                var total: Int  = category?.totalInCart
                total++
                if(total <= 10) {
                    category.totalInCart = total
                    clickListener.updateCartClickListener(category)
                    tvCount.text = total.toString()
                }
            }

            Glide.with(thumbImage)
                .load(category?.url)
                .into(thumbImage)

        }
    }

    interface CategoryListClickListener {
        fun addToCartClickListener(category: Category)
        fun updateCartClickListener(category: Category)
        fun removeFromCartClickListener(category: Category)
    }
}