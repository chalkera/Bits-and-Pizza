package com.example.pizza

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

internal class CaptionedImagesAdapter(private val captions: Array<String>, private val imageIds: IntArray) : RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder?>() {
    private var listener: Listener? = null

    internal interface Listener {
        fun onClick(position: Int)
    }

    class ViewHolder(v: CardView) : RecyclerView.ViewHolder(v) {
        var cardView: CardView? = null

        init {
            cardView = v
        }
    }

    override fun getItemCount(): Int {
        return captions.size
    }

    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context).inflate(R.layout.card_captioned_image, parent, false) as CardView
        return ViewHolder(cv)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.cardView
        val imageView = cardView?.findViewById(R.id.info_image) as ImageView
        val drawable = ContextCompat.getDrawable(cardView.context, imageIds[position])

        imageView.setImageDrawable(drawable)
        imageView.contentDescription = captions[position]

        val textView = cardView.findViewById(R.id.info_text) as TextView
        textView.text = captions[position]

        cardView.setOnClickListener {
            if (listener != null) {
                listener!!.onClick(position)
            }
        }
    }
}