package com.example.evaluasi

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Barang(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nama_barang")
    val namaBarang: String,
    @SerializedName("kategori")
    val kategori: String,
    @SerializedName("stok")
    val stok: Int,
    @SerializedName("harga")
    val harga: Int,
    @SerializedName("deskripsi")
    val deskripsi: String
) : Serializable