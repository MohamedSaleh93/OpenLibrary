package com.mohamed.opendocumentlibrary.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohamed.opendocumentlibrary.databinding.ListItemDocumentBinding
import com.mohamed.opendocumentlibrary.model.Document
import com.mohamed.opendocumentlibrary.ui.DetailsActivity
import com.mohamed.opendocumentlibrary.utils.Constants

class DocumentsAdapter(private val documentsList: List<Document>): RecyclerView.Adapter<DocumentsAdapter.DocumentsViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentsViewHolder {
		val binding = ListItemDocumentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return DocumentsViewHolder(binding)
	}

	override fun onBindViewHolder(holder: DocumentsViewHolder, position: Int) {
		holder.bindView(documentsList[position])
	}

	override fun getItemCount(): Int {
		return documentsList.size
	}

	class DocumentsViewHolder(private val binding: ListItemDocumentBinding): RecyclerView.ViewHolder(binding.root) {
		fun bindView(document: Document) {
			binding.docTitle.text = document.title
			binding.docAuthor.text = document.author

			itemView.setOnClickListener {
				val intent = Intent(itemView.context, DetailsActivity::class.java)
				intent.putExtra(Constants.DETAILS_SCREEN_DATA, document)
				itemView.context.startActivity(intent)
			}
		}
	}
}