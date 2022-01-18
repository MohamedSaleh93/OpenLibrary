package com.mohamed.opendocumentlibrary.network

import com.mohamed.opendocumentlibrary.model.CallResult

interface INetworkCall {

	fun createGetRequest(providedUrl: String): CallResult
}