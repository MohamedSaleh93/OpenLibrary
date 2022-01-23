package com.mohamed.opendocumentlibrary.ui.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mohamed.opendocumentlibrary.R
import com.mohamed.opendocumentlibrary.databinding.ListItemIsbnBinding
import java.net.URL
import java.util.concurrent.Executors

class ISBNAdapter(
	private val isbnList: List<String>
	): RecyclerView.Adapter<ISBNAdapter.ISBNViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ISBNViewHolder {
		val binding = ListItemIsbnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return ISBNViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ISBNViewHolder, position: Int) {
		holder.bind(isbnList[position])
	}

	override fun getItemCount(): Int {
		return isbnList.size
	}

	class ISBNViewHolder(private val binding: ListItemIsbnBinding): RecyclerView.ViewHolder(binding.root) {
		fun bind(isbn: String) {
			val imageURL = "https://covers.openlibrary.org/b/isbn/${isbn}-M.jpg?default=false"

			binding.isbnTV.text = isbn

			Glide.with(binding.root)
				.load(imageURL)
				.placeholder(R.drawable.ic_placeholder)
				.into(binding.isbnCover)
		}
	}

}