import com.example.railwayreservation.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.railwayreservation.passenger.models.Category


class PlaceYourTicketAdapter(val categoryList: List<Category?>?): RecyclerView.Adapter<PlaceYourTicketAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceYourTicketAdapter.MyViewHolder {
       val view: View = LayoutInflater.from(parent.context).inflate(R.layout.placeyourticket_list_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(categoryList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if(categoryList == null) 0  else categoryList.size
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val thumbImage: ImageView = view.findViewById(R.id.thumbImage)
        val menuName: TextView = view.findViewById(R.id.menuName)
        val menuPrice: TextView = view.findViewById(R.id.menuPrice)
        val menuQty: TextView = view.findViewById(R.id.menuQty)

        fun bind(category: Category) {
            menuName.text = category?.name!!
            menuPrice.text = "Price RM" + String.format("%.2f",category?.price * category.totalInCart)
            menuQty.text = "Quantity :" + category?.totalInCart

            Glide.with(thumbImage)
                .load(category?.url)
                .into(thumbImage)

        }
    }
}