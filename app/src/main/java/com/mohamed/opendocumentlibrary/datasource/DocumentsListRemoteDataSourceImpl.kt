package com.mohamed.opendocumentlibrary.datasource

import com.mohamed.opendocumentlibrary.model.Document
import com.mohamed.opendocumentlibrary.model.RequestDocumentResult
import com.mohamed.opendocumentlibrary.network.INetworkCall
import com.mohamed.opendocumentlibrary.parser.IParser
import com.mohamed.opendocumentlibrary.utils.Constants

class DocumentsListRemoteDataSourceImpl(
	private val networkCall: INetworkCall,
	private val parser: IParser
	): IDocumentsListDataSource {

	override fun requestDocuments(searchQuery: String): RequestDocumentResult {
		val call = networkCall.createGetRequest(Constants.BASE_URL + searchQuery)
		return if (call.exception == null) {
			parser.parseJsonStringToDocumentResultResponse(call.result!!)
		} else {
			RequestDocumentResult(exception = call.exception)
		}
	}
}