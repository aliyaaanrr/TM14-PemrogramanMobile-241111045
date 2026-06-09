package com.example.evaluasi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: BarangAdapter
    private lateinit var rvBarang: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvBarang = findViewById(R.id.rvBarang)
        progressBar = findViewById(R.id.progressBar)
        rvBarang.layoutManager = LinearLayoutManager(this)

        fetchData()
    }

    private fun fetchData() {
        progressBar.visibility = View.VISIBLE
        RetrofitClient.instance.getBarang().enqueue(object : Callback<BarangResponse> {
            override fun onResponse(call: Call<BarangResponse>, response: Response<BarangResponse>) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val listBarang = response.body()?.data ?: emptyList()
                    setupRecyclerView(listBarang)
                } else {
                    val errorMsg = response.errorBody()?.string() ?: "Unknown error"
                    Log.e("API_ERROR", "Response Error: ${response.code()} - $errorMsg")
                    Toast.makeText(this@MainActivity, "Gagal: ${response.code()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<BarangResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                Log.e("API_ERROR", "Error: ${t.message}")
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupRecyclerView(listBarang: List<Barang>) {
        adapter = BarangAdapter(listBarang) { barang ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("EXTRA_BARANG", barang)
            startActivity(intent)
        }
        rvBarang.adapter = adapter
    }
}
