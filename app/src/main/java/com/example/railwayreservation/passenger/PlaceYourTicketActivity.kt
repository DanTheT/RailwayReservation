package com.example.railwayreservation.passenger

import PlaceYourTicketAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.passenger.models.CategoryModel

class PlaceYourTicketActivity : AppCompatActivity() {

    var placeYourTicketAdapter: PlaceYourTicketAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_your_ticket)

        val categoryModel: CategoryModel? = intent.getParcelableExtra("CategoryModel")


        val buttonPlaceYourOrder = findViewById<TextView>(R.id.buttonPlaceYourOrder)
        buttonPlaceYourOrder.setOnClickListener {
            startActivity(Intent(this,ConfirmPayment::class.java))
        }


        initRecyclerView(categoryModel)
        calculateTotalAmount(categoryModel)
    }

    private fun initRecyclerView(categoryModel: CategoryModel?) {
        val cartItemsRecyclerView = findViewById<RecyclerView>(R.id.cartItemsRecyclerView)
        cartItemsRecyclerView.layoutManager = LinearLayoutManager(this)
        placeYourTicketAdapter = PlaceYourTicketAdapter(categoryModel?.category)
        cartItemsRecyclerView.adapter = placeYourTicketAdapter
    }

    private fun calculateTotalAmount(categoryModel: CategoryModel?) {
        var subTotalAmount = 0f
        for(category in categoryModel?.category!!) {
            subTotalAmount += category?.price!! * category?.totalInCart!!

        }
        var tvSubtotalAmount = findViewById<TextView>(R.id.tvTotalAmount)
        var tvTotalAmount = findViewById<TextView>(R.id.tvTotalAmount)

        tvSubtotalAmount.text = "RM"+ String.format("%.2f", subTotalAmount)


        tvTotalAmount.text = "RM"+ String.format("%.2f", subTotalAmount)
    }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            if (requestCode == 1000) {
                setResult(RESULT_OK)
                finish()
            }
            super.onActivityResult(requestCode, resultCode, data)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                android.R.id.home -> finish()
                else -> {}
            }
            return super.onOptionsItemSelected(item)
        }

        override fun onBackPressed() {
            super.onBackPressed()
            setResult(RESULT_CANCELED)
            finish()
        }
    }

