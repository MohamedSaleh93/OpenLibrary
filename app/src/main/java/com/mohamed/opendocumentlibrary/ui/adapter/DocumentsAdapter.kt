package com.mohamed.opendocumentlibrary.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mohamed.opendocumentlibrary.R
import com.mohamed.opendocumentlibrary.model.Document
import com.mohamed.opendocumentlibrary.ui.DetailsActivity
import com.mohamed.opendocumentlibrary.utils.Constants

class DocumentsAdapter(private val documentsList: List<Document>): RecyclerView.Adapter<DocumentsAdapter.DocumentsViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentsViewHolder {
		return DocumentsViewHolder(LayoutInflater.from(parent.context)
			.inflate(R.layout.list_item_document, parent, false))
	}

	override fun onBindViewHolder(holder: DocumentsViewHolder, position: Int) {
		holder.bindView(documentsList[position])
	}

	override fun getItemCount(): Int {
		return documentsList.size
	}

	class DocumentsViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
		fun bindView(document: Document) {
			val title = itemView.findViewById<TextView>(R.id.docTitle)
			val author = itemView.findViewById<TextView>(R.id.docAuthor)

			title.text = document.title
			author.text = document.author

			itemView.setOnClickListener {
				val intent = Intent(itemView.context, DetailsActivity::class.java)
				intent.putExtra(Constants.DETAILS_SCREEN_DATA, document)
				itemView.context.startActivity(intent)
			}
		}
	}
}