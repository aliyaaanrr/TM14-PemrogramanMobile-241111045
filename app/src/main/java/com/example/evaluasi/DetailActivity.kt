package com.example.evaluasi

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val barang = intent.getSerializableExtra("EXTRA_BARANG") as? Barang

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<ImageButton>(R.id.btnShare).setOnClickListener {
            Toast.makeText(this, "Share ${barang?.namaBarang}", Toast.LENGTH_SHORT).show()
        }

        if (barang != null) {
            findViewById<TextView>(R.id.tvDetailNama).text = barang.namaBarang
            findViewById<TextView>(R.id.tvDetailKategori).text = barang.kategori.uppercase()
            findViewById<TextView>(R.id.tvDetailStok).text = "${barang.stok} Unit"
            findViewById<TextView>(R.id.tvDetailDeskripsi).text = barang.deskripsi

            val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
            findViewById<TextView>(R.id.tvDetailHarga).text = format.format(barang.harga)
        }
    }
}
