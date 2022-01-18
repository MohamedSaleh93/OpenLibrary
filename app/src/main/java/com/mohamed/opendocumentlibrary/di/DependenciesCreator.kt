package com.mohamed.opendocumentlibrary.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mohamed.opendocumentlibrary.datasource.DocumentsListRemoteDataSourceImpl
import com.mohamed.opendocumentlibrary.datasource.IDocumentsListDataSource
import com.mohamed.opendocumentlibrary.network.INetworkCall
import com.mohamed.opendocumentlibrary.network.NetworkCall
import com.mohamed.opendocumentlibrary.parser.IParser
import com.mohamed.opendocumentlibrary.parser.Parser
import com.mohamed.opendocumentlibrary.repository.DocumentsListRepositoryImpl
import com.mohamed.opendocumentlibrary.repository.IDocumentsListRepository
import com.mohamed.opendocumentlibrary.usecase.GetDocumentsUseCase
import com.mohamed.opendocumentlibrary.usecase.IGetDocumentsUseCase
import com.mohamed.opendocumentlibrary.viewmodel.MainViewModel
import com.mohamed.opendocumentlibrary.viewmodel.MainViewModelFactory

object DependenciesCreator {

	fun provideMainViewModel(activity: AppCompatActivity): MainViewModel {
		return ViewModelProvider(activity, provideMainViewModelFactory()).get(MainViewModel::class.java)
	}

	private fun provideMainViewModelFactory(): MainViewModelFactory {
		return MainViewModelFactory(provideGetDocumentsUseCase())
	}

	private fun provideGetDocumentsUseCase(): IGetDocumentsUseCase {
		return GetDocumentsUseCase(provideDocumentsListRepository())
	}

	private fun provideDocumentsListRepository(): IDocumentsListRepository {
		return DocumentsListRepositoryImpl(provideDocumentsListRemoteDataSource())
	}

	private fun provideDocumentsListRemoteDataSource(): IDocumentsListDataSource {
		return DocumentsListRemoteDataSourceImpl(provideNetworkCall(), provideParser())
	}

	private fun provideNetworkCall(): INetworkCall {
		return NetworkCall()
	}

	private fun provideParser(): IParser {
		return Parser()
	}
}