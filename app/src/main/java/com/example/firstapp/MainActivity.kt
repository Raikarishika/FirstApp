package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.example.firstapp.adapters.MyAdapter
import com.firebase.ui.auth.AuthUI

class MainActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textview = findViewById<TextView>(R.id.textview_main)


        val goto_profile_button = findViewById<Button>(R.id.main_profile_button)
        goto_profile_button.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        AndroidNetworking.initialize(applicationContext)

        AndroidNetworking.get("https://doubtconnect.in:3001/")
            .build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {
                    Log.d("MainActivity", "Response from server:$response")
                    textview.text = response
                }

                override fun onError(anError: ANError?) {
                    Log.e("MainActivity", "Error:${anError.toString()}")
                }
            })
                val int = listOf("1")

        val recycler_view = findViewById<RecyclerView>(R.id.recyclerview)

        val intent = Intent(this,MainActivity::class.java)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = MyAdapter(int)
    }
    suspend fun db(){
        val colorDao = ColorDataBase.getInstance(applicationContext).colorDao()


    }

}








