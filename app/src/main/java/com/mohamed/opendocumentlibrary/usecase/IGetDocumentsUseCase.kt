package com.mohamed.opendocumentlibrary.usecase

interface IGetDocumentsUseCase {

	fun getDocumentsResultFromQuery(searchQuery: String, execute: Execute): Thread
}