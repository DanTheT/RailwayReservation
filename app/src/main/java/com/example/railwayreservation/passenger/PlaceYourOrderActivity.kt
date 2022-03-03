package com.example.railwayreservation.passenger

import PlaceYourOrderAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.passenger.models.RestaurentModel

class PlaceYourOrderActivity : AppCompatActivity() {

    var placeYourOrderAdapter: PlaceYourOrderAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_your_order)

        val restaurantModel: RestaurentModel? = intent.getParcelableExtra("RestaurantModel")
        val actionbar: ActionBar? = supportActionBar
        actionbar?.setTitle(restaurantModel?.name)
        actionbar?.setSubtitle(restaurantModel?.address)
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val buttonPlaceYourOrder = findViewById<TextView>(R.id.buttonPlaceYourOrder)
        buttonPlaceYourOrder.setOnClickListener {
            startActivity(Intent(this,ConfirmPayment::class.java))
        }


        initRecyclerView(restaurantModel)
        calculateTotalAmount(restaurantModel)
    }

    private fun initRecyclerView(restaurantModel: RestaurentModel?) {
        val cartItemsRecyclerView = findViewById<RecyclerView>(R.id.cartItemsRecyclerView)
        cartItemsRecyclerView.layoutManager = LinearLayoutManager(this)
        placeYourOrderAdapter = PlaceYourOrderAdapter(restaurantModel?.menus)
        cartItemsRecyclerView.adapter = placeYourOrderAdapter
    }

    private fun calculateTotalAmount(restaurantModel: RestaurentModel?) {
        var subTotalAmount = 0f
        for(menu in restaurantModel?.menus!!) {
            subTotalAmount += menu?.price!! * menu?.totalInCart!!

        }

        var tvTotalAmount = findViewById<TextView>(R.id.tvTotalAmount)
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

