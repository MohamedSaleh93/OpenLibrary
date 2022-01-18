package com.mohamed.opendocumentlibrary.repository

import com.mohamed.opendocumentlibrary.datasource.IDocumentsListDataSource
import com.mohamed.opendocumentlibrary.model.Document

class DocumentsListRepositoryImpl(
	private val documentsRemoteDataSource: IDocumentsListDataSource
	): IDocumentsListRepository {

	override fun requestDocuments(searchQuery: String): List<Document> {
		return documentsRemoteDataSource.requestDocuments(searchQuery)
	}
}