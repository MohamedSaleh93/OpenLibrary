package com.mohamed.opendocumentlibrary.usecase

import com.mohamed.opendocumentlibrary.model.Document
import com.mohamed.opendocumentlibrary.repository.IDocumentsListRepository

class GetDocumentsUseCase(
	private val documentsListRepository: IDocumentsListRepository
	): IGetDocumentsUseCase {

	override fun getDocumentsResultFromQuery(searchQuery: String): List<Document> {
		return documentsListRepository.requestDocuments(searchQuery)
	}
}