package com.example.pizza

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.pizza.model.Pizza

class PizzaDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PIZZA_ID = "pizzaId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val pizzaId = intent.extras!![EXTRA_PIZZA_ID] as Int

        val pizzaName: String = Pizza.pizzas[pizzaId].name
        val textView = findViewById<View>(R.id.pizza_text) as TextView
        textView.text = pizzaName

        val pizzaImage: Int = Pizza.pizzas[pizzaId].imageResourceId
        val imageView :ImageView = findViewById(R.id.pizza_image)
        imageView.setImageDrawable(ContextCompat.getDrawable(this, pizzaImage))
        imageView.contentDescription = pizzaName
    }
}