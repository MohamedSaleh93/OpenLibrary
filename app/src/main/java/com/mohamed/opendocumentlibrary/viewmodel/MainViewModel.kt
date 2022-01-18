package com.mohamed.opendocumentlibrary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohamed.opendocumentlibrary.usecase.IGetDocumentsUseCase
import com.mohamed.opendocumentlibrary.viewstate.DocumentsListViewState
import com.mohamed.opendocumentlibrary.viewstate.LoadingState

class MainViewModel(
	private val getDocumentsUseCase: IGetDocumentsUseCase
) : ViewModel() {

	val documentsListState = MutableLiveData<DocumentsListViewState>()
	private lateinit var thread: Thread

	fun getDocumentList() {
		thread = Thread {

		}
		documentsListState.postValue(LoadingState)
		thread.start()
	}

	override fun onCleared() {
		super.onCleared()
		if (::thread.isInitialized) {
			thread.interrupt()
		}
	}

}