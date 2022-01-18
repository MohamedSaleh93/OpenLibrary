package com.mohamed.opendocumentlibrary.ui.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mohamed.opendocumentlibrary.R
import java.net.URL
import java.util.concurrent.Executors

class ISBNAdapter(
	private val isbnList: List<String>
	): RecyclerView.Adapter<ISBNAdapter.ISBNViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ISBNViewHolder {
		return ISBNViewHolder(LayoutInflater.from(parent.context)
			.inflate(R.layout.list_item_isbn, parent, false))
	}

	override fun onBindViewHolder(holder: ISBNViewHolder, position: Int) {
		holder.bind(isbnList[position])
	}

	override fun getItemCount(): Int {
		return isbnList.size
	}

	class ISBNViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
		fun bind(isbn: String) {
			val isbnTV = itemView.findViewById<TextView>(R.id.isbnTV)
			val coverImage = itemView.findViewById<ImageView>(R.id.isbnCover)

			isbnTV.text = isbn

			val executor = Executors.newSingleThreadExecutor()

			val handler = Handler(Looper.getMainLooper())

			var image: Bitmap

			executor.execute {
				val imageURL = "https://covers.openlibrary.org/b/isbn/${isbn}-M.jpg?default=false"
				try {
					val `in` = URL(imageURL).openStream()
					image = BitmapFactory.decodeStream(`in`)

					handler.post {
						coverImage.setImageBitmap(image)
					}
				} catch (e: Exception) {
					handler.post {
						coverImage.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_broken_image))
					}
				}
			}
		}
	}

}