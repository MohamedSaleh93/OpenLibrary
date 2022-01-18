package com.mohamed.opendocumentlibrary.repository

import com.mohamed.opendocumentlibrary.model.RequestDocumentResult

interface IDocumentsListRepository {

	fun requestDocuments(searchQuery: String): RequestDocumentResult
}