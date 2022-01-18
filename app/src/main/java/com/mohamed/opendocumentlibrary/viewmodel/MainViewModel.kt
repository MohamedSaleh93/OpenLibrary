package com.mohamed.opendocumentlibrary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohamed.opendocumentlibrary.model.Document
import com.mohamed.opendocumentlibrary.usecase.IGetDocumentsUseCase
import com.mohamed.opendocumentlibrary.usecase.Execute
import com.mohamed.opendocumentlibrary.viewstate.DocumentsDataListResult
import com.mohamed.opendocumentlibrary.viewstate.DocumentsListViewState
import com.mohamed.opendocumentlibrary.viewstate.ErrorState
import com.mohamed.opendocumentlibrary.viewstate.LoadingState
import java.lang.Exception

class MainViewModel(
	private val getDocumentsUseCase: IGetDocumentsUseCase
) : ViewModel() {

	val documentsListState = MutableLiveData<DocumentsListViewState>()
	private lateinit var thread: Thread

	fun getDocumentList(searchQuery: String) {
		thread = getDocumentsUseCase.getDocumentsResultFromQuery(searchQuery, object: Execute {
			override fun onSuccess(result: List<Document>) {
				documentsListState.postValue(DocumentsDataListResult(result))
			}

			override fun onError(ex: Exception) {
				documentsListState.postValue(ErrorState(ex.localizedMessage))
			}

		})
		documentsListState.postValue(LoadingState)
	}

	override fun onCleared() {
		super.onCleared()
		if (::thread.isInitialized) {
			thread.interrupt()
		}
	}

}