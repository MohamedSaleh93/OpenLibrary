package com.mohamed.opendocumentlibrary.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamed.opendocumentlibrary.databinding.ActivityDetailsBinding
import com.mohamed.opendocumentlibrary.model.Document
import com.mohamed.opendocumentlibrary.ui.adapter.ISBNAdapter
import com.mohamed.opendocumentlibrary.utils.Constants

class DetailsActivity: AppCompatActivity() {

	private lateinit var binding: ActivityDetailsBinding
	private lateinit var isbnAdapter: ISBNAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityDetailsBinding.inflate(layoutInflater)
		val view = binding.root
		setContentView(view)
		if (intent != null) {
			val document = intent.getParcelableExtra<Document>(Constants.DETAILS_SCREEN_DATA)
			document?.let {
				bindTheUiComponent(it)
			}
		}
		binding.detailsTitleTV.setOnClickListener {
			startMainScreenWithData(binding.detailsTitleTV.text.toString())
		}
		binding.detailsAuthorTV.setOnClickListener {
			startMainScreenWithData(binding.detailsAuthorTV.text.toString())
		}
	}

	private fun bindTheUiComponent(document: Document) {
		with(document) {
			binding.detailsTitleTV.text = title
			binding.detailsAuthorTV.text = author
			isbnAdapter = ISBNAdapter(isbn)
		}
		binding.isbnRV.layoutManager = LinearLayoutManager(this)
		binding.isbnRV.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
		binding.isbnRV.adapter = isbnAdapter
	}

	private fun startMainScreenWithData(data: String) {
		val intent = Intent(this@DetailsActivity, MainActivity::class.java)
		intent.putExtra(Constants.MAIN_SCREEN_DATA, data)
		startActivity(intent)
	}
}