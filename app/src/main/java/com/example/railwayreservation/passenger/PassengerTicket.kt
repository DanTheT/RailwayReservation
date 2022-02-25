package com.example.railwayreservation.passenger

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.railwayreservation.R
import com.example.railwayreservation.passenger.transactions.ViewPH
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import android.graphics.drawable.ColorDrawable
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import com.example.railwayreservation.passenger.reservation.MakeReservation


class PassengerTicket : AppCompatActivity() {

    private lateinit var ivQRCode: ImageView
    private lateinit var etData: EditText
    private lateinit var btnGenerate: Button

    private lateinit var btnViewPayHistory: Button
    private lateinit var btnViewCurrentHistory: Button


    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase
    private lateinit var myRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_ticket)

//        //actionbar
//        val actionbar = supportActionBar
//        //set actionbar title
//        actionbar!!.title = "My Ticket"
//        //set back button
//        actionbar.setDisplayHomeAsUpEnabled(true)


        ivQRCode = findViewById(R.id.ivQRCode)
        etData = findViewById(R.id.etData)
        btnGenerate = findViewById(R.id.btnGenerate)
        btnViewPayHistory = findViewById(R.id.btnViewPayHistory)
        btnViewCurrentHistory = findViewById(R.id.btnViewCurrentHistory)

        btnViewPayHistory.setOnClickListener {
            startActivity(Intent(this, ViewPH::class.java))

        }

        btnViewCurrentHistory.setOnClickListener {
            startActivity(Intent(this, MakeReservation::class.java))

        }

        btnGenerate.setOnClickListener {

            val data = etData.text.toString().trim()

            if (data.isEmpty()) {
                Toast.makeText(this, "Please copy and enter your transaction ID", Toast.LENGTH_SHORT).show()
            } else {

                val writer = QRCodeWriter()
                try {


                    val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512)
                    val width = bitMatrix.width
                    val height = bitMatrix.height
                    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                    for (x in 0 until width) {
                        for (y in 0 until height) {
                            bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                        }
                    }

                    ivQRCode.setImageBitmap(bmp)
                } catch (e: WriterException) {
                    e.printStackTrace()

                }

            }
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return true
//    }
}




