package com.mohamed.opendocumentlibrary.di

import com.mohamed.opendocumentlibrary.datasource.DocumentsListRemoteDataSourceImpl
import com.mohamed.opendocumentlibrary.datasource.IDocumentsListDataSource
import org.koin.dsl.module

val dataModule = module {
	single<IDocumentsListDataSource> { DocumentsListRemoteDataSourceImpl(get())}
}