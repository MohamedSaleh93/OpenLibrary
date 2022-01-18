package com.mohamed.opendocumentlibrary.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mohamed.opendocumentlibrary.R
import com.mohamed.opendocumentlibrary.model.Document

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
		}
	}
}