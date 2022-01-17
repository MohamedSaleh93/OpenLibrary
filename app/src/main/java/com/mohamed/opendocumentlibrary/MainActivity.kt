package com.mohamed.opendocumentlibrary

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.options_menu, menu)
		val search = menu.findItem(R.id.search)
		val searchView = search.actionView as SearchView
		searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(p0: String?): Boolean {
				Toast.makeText(this@MainActivity, p0, Toast.LENGTH_LONG).show()
				return false
			}

			override fun onQueryTextChange(p0: String?): Boolean {
				return false
			}

		})
		return true
	}
}