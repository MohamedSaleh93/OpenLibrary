package com.mohamed.opendocumentlibrary.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mohamed.opendocumentlibrary.R
import com.mohamed.opendocumentlibrary.model.Document
import com.mohamed.opendocumentlibrary.utils.Constants

class DetailsActivity: AppCompatActivity() {

	private lateinit var detailsTitle: TextView
	private lateinit var detailsAuthor: TextView
	private lateinit var isbnRV: RecyclerView

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_details)
		buildTheUiComponent()
		if (intent != null) {
			val document = intent.getParcelableExtra<Document>(Constants.DETAILS_SCREEN_DATA)
			document?.let {
				bindTheUiComponent(it)
			}
		}
	}

	private fun buildTheUiComponent() {
		detailsTitle = findViewById(R.id.detailsTitleTV)
		detailsAuthor = findViewById(R.id.detailsAuthorTV)
		isbnRV = findViewById(R.id.isbnRV)

		detailsTitle.setOnClickListener {
			startMainScreenWithData(detailsTitle.text.toString())
		}
		detailsAuthor.setOnClickListener {
			startMainScreenWithData(detailsAuthor.text.toString())
		}
	}

	private fun bindTheUiComponent(document: Document) {
		with(document) {
			detailsTitle.text = title
			detailsAuthor.text = author
		}
	}

	private fun startMainScreenWithData(data: String) {
		val intent = Intent(this@DetailsActivity, MainActivity::class.java)
		intent.putExtra(Constants.MAIN_SCREEN_DATA, data)
		startActivity(intent)
	}
}