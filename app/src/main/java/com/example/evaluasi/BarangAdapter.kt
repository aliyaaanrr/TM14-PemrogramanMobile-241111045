package com.example.evaluasi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale

class BarangAdapter(
    private val listBarang: List<Barang>,
    private val onItemClick: (Barang) -> Unit
) : RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {

    class BarangViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama: TextView = view.findViewById(R.id.tvNamaBarang)
        val tvHarga: TextView = view.findViewById(R.id.tvHargaBarang)
        val tvStok: TextView = view.findViewById(R.id.tvStokBarang)
        val vDot: View = view.findViewById(R.id.vStatusDot)
        val btnDetails: Button = view.findViewById(R.id.btnDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_barang, parent, false)
        return BarangViewHolder(view)
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val barang = listBarang[position]
        holder.tvNama.text = barang.namaBarang
        
        if (barang.stok > 5) {
            holder.tvStok.text = "IN STOCK: ${barang.stok}"
            holder.vDot.setBackgroundResource(R.drawable.shape_dot_green)
        } else {
            holder.tvStok.text = "LOW STOCK: ${barang.stok}"
            holder.vDot.setBackgroundResource(R.drawable.shape_dot_orange)
        }
        
        val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        holder.tvHarga.text = format.format(barang.harga)

        holder.itemView.setOnClickListener { onItemClick(barang) }
        holder.btnDetails.setOnClickListener { onItemClick(barang) }
    }

    override fun getItemCount(): Int = listBarang.size
}
