package com.example.pizza

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        done.setOnClickListener {
            val text: CharSequence = "Your order has been updated"
            val duration: Int = Snackbar.LENGTH_SHORT
            val snackbar: Snackbar = Snackbar.make(findViewById<View>(R.id.coordinator), text, duration)

            snackbar.setAction("Undo", View.OnClickListener {
                val toast = Toast.makeText(this@OrderActivity, "Undone", Toast.LENGTH_SHORT)
                toast.show()
            })
            snackbar.show()
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}