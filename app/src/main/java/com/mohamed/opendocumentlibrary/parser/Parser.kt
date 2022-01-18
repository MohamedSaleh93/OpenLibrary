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
				val title = json.optString("title")
				val author = json.optJSONArray("author_name")
				val isbnList = ArrayList<String>()
				val isbnJsonArray = json.optJSONArray("isbn")
				if (isbnJsonArray != null) {
					for (j in 0 until isbnJsonArray.length()) {
						isbnList.add(isbnJsonArray.getString(j))
					}
				}
				documentList.add(Document(title, isbnList, author?.getString(0) ?: ""))
			}
			return RequestDocumentResult(documentsList = documentList)
		} catch (e: JSONException) {
			RequestDocumentResult(e)
		}
	}
}