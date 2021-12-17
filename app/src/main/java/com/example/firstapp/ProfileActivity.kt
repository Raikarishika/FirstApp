package com.example.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val imageview  = findViewById<ImageView>(R.id.imageView2)

        Glide.with(this)
            .load("https://firebasestorage.googleapis.com/v0/b/firstapp-da3c5.appspot.com/o/df.png?alt=media&token=6e908f9b-725b-4a75-85ac-d68bc982bafb")
            .centerCrop()
            .into(imageview)


        val name_edit = findViewById<EditText>(R.id.editText1)
        val email_edit = findViewById<EditText>(R.id.editText2)
        val sumbit_button = findViewById<Button>(R.id.profile_submit_button)
        val pbar = findViewById<ProgressBar>(R.id.profile_progressbar)


        val databaseref = Firebase.database.getReference("User/${FirebaseAuth.getInstance().uid}")

        databaseref.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                name_edit.setText (snapshot.child("name").value.toString())
                email_edit.setText (snapshot.child("name").value.toString())
                pbar.visibility = View.INVISIBLE
            }

            override fun onCancelled(error:DatabaseError){
                Log.e("ProfileActivity","Firebase database $error")
            }
        })
        sumbit_button.setOnClickListener{
            updateuserinfo(name_edit.text.toString(),email_edit.text.toString())

        }

    }
    private fun updateuserinfo(name:String,email:String?){
        val database = Firebase.database.reference

        val user = User(name,email)
        val map = user.toMap()
        val usermap = mapOf<String,Any>(
            "Users/${FirebaseAuth.getInstance().uid}" to map
        )
        database.updateChildren(usermap).addOnSuccessListener {
            Toast.makeText(this,"User data Updated!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}