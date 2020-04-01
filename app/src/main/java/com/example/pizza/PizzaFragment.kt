package com.example.pizza

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza.model.Pizza

/**
 * A simple [Fragment] subclass.
 */
class PizzaFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val pizzaRecycler: RecyclerView = inflater.inflate(R.layout.fragment_pizza, container, false) as RecyclerView
        val pizzaNames = arrayOfNulls<String>(Pizza.pizzas.size)
        for (i in pizzaNames.indices) {
            pizzaNames[i] = Pizza.pizzas[i].name
        }
        val pizzaImages = IntArray(Pizza.pizzas.size)
        for (i in pizzaImages.indices) {
            pizzaImages[i] = Pizza.pizzas[i].imageResourceId
        }

        val adapter = CaptionedImagesAdapter(pizzaNames as Array<String>, pizzaImages)
        pizzaRecycler.adapter = adapter

        val layoutManager = GridLayoutManager(activity, 2)
        pizzaRecycler.layoutManager = layoutManager

        adapter.setListener(object : CaptionedImagesAdapter.Listener {
            override fun onClick(position: Int) {
                val intent = Intent(activity, PizzaDetailActivity::class.java)
                intent.putExtra(PizzaDetailActivity.EXTRA_PIZZA_ID, position)
                activity?.startActivity(intent)
            }
        })
        return pizzaRecycler
    }
}