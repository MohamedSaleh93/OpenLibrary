package com.mohamed.opendocumentlibrary.repository

import com.mohamed.opendocumentlibrary.model.Document

interface IDocumentsListRepository {

	fun requestDocuments(searchQuery: String): List<Document>
}