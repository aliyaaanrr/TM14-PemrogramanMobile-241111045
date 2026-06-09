package com.example.evaluasi

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("barang.php")
    fun getBarang(): Call<BarangResponse>
}