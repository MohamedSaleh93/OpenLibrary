package com.mohamed.opendocumentlibrary.datasource

import com.mohamed.opendocumentlibrary.model.RequestDocumentResult

interface IDocumentsListDataSource {

	fun requestDocuments(searchQuery: String): RequestDocumentResult
}