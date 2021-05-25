package com.example.naamjaap.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.naamjaap.R
import kotlinx.android.synthetic.main.activity_jaap.*

class JaapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jaap)
    }

    fun incrementValue(view: View) {
        var i = count_view.text.toString().toInt()
        count_view.setText("${++i}")
    }
}