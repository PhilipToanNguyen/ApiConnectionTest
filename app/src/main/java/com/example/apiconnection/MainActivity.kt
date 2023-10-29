package com.example.apiconnection

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.apiconnection.ui.theme.ApiConnectionTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val apiService = ApiClient.create()
        val call = apiService.getUsers()

        call.enqueue(object : Callback<List<Data>> {
            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                if (response.isSuccessful) {
                    val userList = response.body()
                    val body = userList?.get(0)?.body
                    val id = userList?.get(0)?.id
                    val title = userList?.get(0)?.title
                    val userId = userList?.get(0)?.id // Bytt ut dette med den faktiske måten å hente ut bruker-ID fra API-responsen


                    if (userId != null) {
                        Log.d("MainActivity", "Body: $body")
                        Log.d ("MainActivity", "ID: $id")
                        Log.d("MainActivity", "TITLE: $title")
                        Log.d("MainActivity", "Bruker-ID: $userId") // Legg til denne loggen

                    }

                    Log.d("MainActivity", "API-request vellykket") // Legg til denne loggen
                    println()
                    // Gjør noe med dataene her
                } else {
                    Log.e("MainActivity", "Feil ved API-request: ${response.code()}") // Legg til denne loggen
                    // Håndter feil her
                }
            }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                Log.e("MainActivity", "Nettverksfeil: ${t.message}") // Legg til denne loggen
                // Håndter nettverksfeil her
            }
        })
    }
}


