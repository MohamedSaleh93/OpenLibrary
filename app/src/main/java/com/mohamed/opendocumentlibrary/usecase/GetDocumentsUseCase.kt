package com.mohamed.opendocumentlibrary.usecase

import com.mohamed.opendocumentlibrary.repository.IDocumentsListRepository

class GetDocumentsUseCase(
	private val documentsListRepository: IDocumentsListRepository
	): IGetDocumentsUseCase {

	override fun getDocumentsResultFromQuery(searchQuery: String, execute: Execute): Thread {
		val thread = Thread {
			val result = documentsListRepository.requestDocuments(searchQuery.replace(" ", "+"))
			if (result.exception != null) {
				execute.onError(result.exception)
			} else  {
				execute.onSuccess(result.documentsList!!)
			}
		}
		thread.start()
		return thread
	}
}