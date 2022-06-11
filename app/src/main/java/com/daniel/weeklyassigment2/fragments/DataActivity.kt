package com.daniel.weeklyassigment2.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.core.content.ContextCompat
import com.daniel.weeklyassigment2.R

class DataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_order)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.order_data_by, menu)
        return true
    }
}