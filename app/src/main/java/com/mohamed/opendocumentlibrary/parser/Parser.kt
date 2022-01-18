package com.mohamed.opendocumentlibrary.parser

import com.mohamed.opendocumentlibrary.model.Document
import com.mohamed.opendocumentlibrary.model.RequestDocumentResult
import org.json.JSONException
import org.json.JSONObject

class Parser: IParser {

	override fun parseJsonStringToDocumentResultResponse(response: String): RequestDocumentResult {
		return try {
			val jsonObject = JSONObject(response)
			val docsArray = jsonObject.getJSONArray("docs")
			val documentList = ArrayList<Document>()
			for (i in 0 until docsArray.length()) {
				val json = docsArray.getJSONObject(i)
				val title = json.getString("title")
				val author = json.getString("author_name")
				val isbnList = ArrayList<String>()
				val isbnJsonArray = json.getJSONArray("isbn")
				for (j in 0 until isbnJsonArray.length()) {
					isbnList.add(isbnJsonArray.getString(i))
				}
				documentList.add(Document(title, isbnList, author))
			}
			return RequestDocumentResult(documentsList = documentList)
		} catch (e: JSONException) {
			RequestDocumentResult(e)
		}
	}
}