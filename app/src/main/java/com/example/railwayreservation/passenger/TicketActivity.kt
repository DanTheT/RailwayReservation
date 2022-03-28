package com.example.railwayreservation.passenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.passenger.adapter.RailwayListAdapter
import com.example.railwayreservation.passenger.models.CategoryModel
import com.google.gson.Gson
import java.io.*
import java.lang.Exception

class TicketActivity : AppCompatActivity(), RailwayListAdapter.RailwayListClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        val categoryModel = getCategoryData()
        initRecyclerView(categoryModel)
    }

    private fun initRecyclerView(categoryList: List<CategoryModel?>?) {
        val recyclerViewRailway = findViewById<RecyclerView>(R.id.recyclerViewRailway)
        recyclerViewRailway.layoutManager = LinearLayoutManager(this)
        val adapter = RailwayListAdapter(categoryList, this)
        recyclerViewRailway.adapter =adapter
    }

    private fun getCategoryData(): List<CategoryModel?>? {
        val inputStream: InputStream = resources.openRawResource(R.raw.railway)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var n : Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)

            }

        }catch (e: Exception){}
        val jsonStr: String = writer.toString()
        val gson = Gson()
        val categoryModel = gson.fromJson<Array<CategoryModel>>(jsonStr, Array<CategoryModel>::class.java).toList()

        return categoryModel
    }

    override fun onItemClick(categoryModel: CategoryModel) {
        val intent = Intent(this@TicketActivity, TicketMenuActivity::class.java)
        intent.putExtra("CategoryModel", categoryModel)
        startActivity(intent)
    }
}
