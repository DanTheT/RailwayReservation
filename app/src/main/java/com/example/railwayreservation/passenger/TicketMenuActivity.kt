package com.example.railwayreservation.passenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.passenger.adapter.CategoryListAdapter
import com.example.railwayreservation.passenger.models.Category
import com.example.railwayreservation.passenger.models.CategoryModel

class TicketMenuActivity : AppCompatActivity() {

    private var itemsInTheCartList: MutableList<Category?>? = null
    private var totalItemInCartCount = 0
    private  var categoryList: List<Category?>? = null
    private var CategoryListAdapter: CategoryListAdapter? = null
    private lateinit var checkoutButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_menu)

        val categoryModel = intent?.getParcelableExtra<CategoryModel>("CategoryModel")


        categoryList = categoryModel?.category

        initRecyclerView(categoryList)
        var checkoutButton = findViewById<TextView>(R.id.checkoutButton)
        checkoutButton.setOnClickListener {
            if(itemsInTheCartList != null && itemsInTheCartList!!.size <= 0) {
                Toast.makeText(this@TicketMenuActivity, "Please add some tickets", Toast.LENGTH_LONG).show()
            }
            else {
                categoryModel?.category = itemsInTheCartList
                val intent = Intent(this@TicketMenuActivity, PlaceYourTicketActivity::class.java)
                intent.putExtra("CategoryModel", categoryModel)
                startActivityForResult(intent, 1000)
            }
        }

    }
    private fun initRecyclerView(category: List<Category?>?) {
        val seatCatRecyclerView = findViewById<RecyclerView>(R.id.seatCatRecyclerView)
        seatCatRecyclerView.layoutManager = GridLayoutManager(this, 2)
        CategoryListAdapter = CategoryListAdapter(category, this)
        seatCatRecyclerView.adapter =CategoryListAdapter
    }

    fun addToCartClickListener(category: Category) {

        checkoutButton = findViewById<TextView>(R.id.checkoutButton)

        if(itemsInTheCartList == null) {
            itemsInTheCartList = ArrayList()
        }
        itemsInTheCartList?.add(category)
        totalItemInCartCount = 0
        for(menu in itemsInTheCartList!!) {
            totalItemInCartCount = totalItemInCartCount + menu?.totalInCart!!
        }
        checkoutButton.text = "Checkout (" + totalItemInCartCount +") Tickets"

    }

    fun updateCartClickListener(category: Category) {
        val index = itemsInTheCartList!!.indexOf(category)
        itemsInTheCartList?.removeAt(index)
        itemsInTheCartList?.add(category)
        totalItemInCartCount = 0
        for(menu in itemsInTheCartList!!) {
            totalItemInCartCount = totalItemInCartCount + menu?.totalInCart!!
        }
        checkoutButton.text = "Checkout (" + totalItemInCartCount +") Tickets"
    }

    fun removeFromCartClickListener(category: Category) {
        if(itemsInTheCartList!!.contains(category)) {
            itemsInTheCartList?.remove(category)
            totalItemInCartCount = 0
            for(menu in itemsInTheCartList!!) {
                totalItemInCartCount = totalItemInCartCount + menu?.totalInCart!!
            }
            checkoutButton.text = "Checkout (" + totalItemInCartCount +") Tickets"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1000 && resultCode == RESULT_OK) {
            finish()
        }
    }
}