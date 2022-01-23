package com.mohamed.opendocumentlibrary.ui

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamed.opendocumentlibrary.R
import com.mohamed.opendocumentlibrary.databinding.ActivityMainBinding
import com.mohamed.opendocumentlibrary.di.DependenciesCreator
import com.mohamed.opendocumentlibrary.model.Document
import com.mohamed.opendocumentlibrary.ui.adapter.DocumentsAdapter
import com.mohamed.opendocumentlibrary.utils.Constants
import com.mohamed.opendocumentlibrary.viewmodel.MainViewModel
import com.mohamed.opendocumentlibrary.viewstate.DocumentsDataListResult
import com.mohamed.opendocumentlibrary.viewstate.ErrorState
import com.mohamed.opendocumentlibrary.viewstate.LoadingState

class MainActivity : AppCompatActivity() {

	private lateinit var mainViewModel: MainViewModel
	private lateinit var binding: ActivityMainBinding
	private lateinit var documentsAdapter: DocumentsAdapter
	private var searchDataFromDetails = ""

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		val view = binding.root
		setContentView(view)
		searchDataFromDetails = intent.getStringExtra(Constants.MAIN_SCREEN_DATA) ?: ""

		mainViewModel = DependenciesCreator.provideMainViewModel(this)

		mainViewModel.documentsListState.observe(this, Observer {
			when(it) {
				is LoadingState -> showLoadingScreenAndHideTheRest()
				is ErrorState -> showErrorMessageToastAndHideTheRest(it.errorMessage)
				is DocumentsDataListResult -> showDocumentsListAndHideThRest(it.documents)
			}
		})
		if (searchDataFromDetails.isNotEmpty()) {
			mainViewModel.getDocumentList(searchDataFromDetails)
		}
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		if (searchDataFromDetails.isEmpty()) {
			menuInflater.inflate(R.menu.options_menu, menu)
			val search = menu.findItem(R.id.search)
			val searchView = search.actionView as SearchView
			searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
				override fun onQueryTextSubmit(p0: String?): Boolean {
					p0?.let {
						mainViewModel.getDocumentList(it)
					}
					return false
				}

				override fun onQueryTextChange(p0: String?): Boolean {
					return false
				}

			})
			return true
		} else {
			return false
		}
	}

	private fun showLoadingScreenAndHideTheRest() {
		binding.docRV.visibility = View.GONE
		binding.descriptionTV.visibility = View.GONE
		binding.loadingProgressBar.visibility = View.VISIBLE
	}

	private fun showErrorMessageToastAndHideTheRest(errorMessage:String) {
		binding.docRV.visibility = View.GONE
		binding.loadingProgressBar.visibility = View.GONE
		binding.descriptionTV.visibility = View.VISIBLE
		Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
	}

	private fun showDocumentsListAndHideThRest(documentsList: List<Document>) {
		binding.loadingProgressBar.visibility = View.GONE
		binding.descriptionTV.visibility = View.GONE
		binding.docRV.visibility = View.VISIBLE

		documentsAdapter = DocumentsAdapter(documentsList)

		binding.docRV.layoutManager = LinearLayoutManager(this)
		binding.docRV.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
		binding.docRV.adapter = documentsAdapter
	}
}