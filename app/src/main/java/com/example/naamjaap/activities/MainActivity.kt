package com.example.naamjaap.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.naamjaap.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_jaap.setOnClickListener {
            var intent = Intent(this, JaapActivity::class.java)
            startActivity(intent)
        }
        btn_today_japkarta.setOnClickListener {
            var intent = Intent(this, AajKaJaapActivity::class.java)
            startActivity(intent)
        }
        btn_most_japkarta.setOnClickListener {
            var intent = Intent(this, AbTkJaapActivity::class.java)
            startActivity(intent)
        }
        btn_sandesh.setOnClickListener {
            var intent = Intent(this,SandeshActivity::class.java)
            startActivity(intent)
        }

    }
}