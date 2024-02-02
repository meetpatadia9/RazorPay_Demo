package com.ipsmeet.razorpaydemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DefaultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default)
     /*   findViewById<Button>(R.id.btn_java).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }*/

        findViewById<Button>(R.id.btn_kotlin).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}