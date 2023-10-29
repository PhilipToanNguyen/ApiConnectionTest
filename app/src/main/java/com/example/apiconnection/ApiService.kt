package com.example.apiconnection

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/posts") // Endepunktet du vil kalle
    fun getUsers(): Call<List<Data>> // Endre typen avhengig av API-responsen
}