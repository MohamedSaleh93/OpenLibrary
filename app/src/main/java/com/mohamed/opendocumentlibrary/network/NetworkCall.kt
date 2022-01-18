package com.mohamed.opendocumentlibrary.network

import android.accounts.NetworkErrorException
import com.mohamed.opendocumentlibrary.model.CallResult
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class NetworkCall: INetworkCall {

	override fun createGetRequest(providedUrl: String): CallResult {
		return try {
			val url = URL(providedUrl)
			val httpURLConnection = url.openConnection() as HttpURLConnection
			val responseCode = httpURLConnection.responseCode
			if (responseCode == HttpURLConnection.HTTP_OK) {
				readStream(httpURLConnection.inputStream)
			} else {
				CallResult(NetworkErrorException("Network error, please try again later"))
			}
		} catch (ex: Exception) {
			CallResult(ex)
		}
	}

	private fun readStream(inS: InputStream): CallResult {
		var reader: BufferedReader? = null
		val response = StringBuffer()
		var result: CallResult
		try {
			reader = BufferedReader(InputStreamReader(inS))
			var line: String? = reader.readLine()
			while (line != null) {
				response.append(line)
				line = reader.readLine()
			}
			result = CallResult(result = response.toString())
		} catch (e: IOException) {
			result = CallResult(e)
		} finally {
			if (reader != null) {
				try {
					reader.close()
				} catch (ex: IOException) {
					result = CallResult(ex)
				}
			}
		}
		return result
	}


}