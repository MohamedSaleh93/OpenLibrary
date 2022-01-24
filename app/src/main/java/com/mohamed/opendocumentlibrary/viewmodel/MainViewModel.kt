package com.mohamed.opendocumentlibrary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.opendocumentlibrary.usecase.IGetDocumentsUseCase
import com.mohamed.opendocumentlibrary.viewstate.DocumentsDataListResult
import com.mohamed.opendocumentlibrary.viewstate.DocumentsListViewState
import com.mohamed.opendocumentlibrary.viewstate.ErrorState
import com.mohamed.opendocumentlibrary.viewstate.LoadingState
import kotlinx.coroutines.launch

class MainViewModel(
	private val getDocumentsUseCase: IGetDocumentsUseCase
) : ViewModel() {

	val documentsListState = MutableLiveData<DocumentsListViewState>()

	fun getDocumentList(searchQuery: String) {
		viewModelScope.launch {
			val result = getDocumentsUseCase.getDocumentsResultFromQuery(searchQuery)
			if (result.exception != null) {
				documentsListState.postValue(ErrorState(result.exception.localizedMessage!!))
			} else  {
				documentsListState.postValue(DocumentsDataListResult(result.documentsList!!))
			}
		}

		documentsListState.postValue(LoadingState)
	}

}