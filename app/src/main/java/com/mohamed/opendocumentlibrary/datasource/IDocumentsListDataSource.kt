package com.mohamed.opendocumentlibrary.datasource

import com.mohamed.opendocumentlibrary.model.Document

interface IDocumentsListDataSource {

	fun requestDocuments(searchQuery: String): List<Document>
}