package com.mohamed.opendocumentlibrary.repository

import com.mohamed.opendocumentlibrary.datasource.IDocumentsListDataSource
import com.mohamed.opendocumentlibrary.model.Document
import com.mohamed.opendocumentlibrary.model.RequestDocumentResult

class DocumentsListRepositoryImpl(
	private val documentsRemoteDataSource: IDocumentsListDataSource
	): IDocumentsListRepository {

	override fun requestDocuments(searchQuery: String): RequestDocumentResult {
		return documentsRemoteDataSource.requestDocuments(searchQuery)
	}
}