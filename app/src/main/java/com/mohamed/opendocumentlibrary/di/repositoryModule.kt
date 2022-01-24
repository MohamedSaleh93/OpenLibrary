package com.mohamed.opendocumentlibrary.di

import com.mohamed.opendocumentlibrary.repository.DocumentsListRepositoryImpl
import com.mohamed.opendocumentlibrary.repository.IDocumentsListRepository
import org.koin.dsl.module

val repositoryModule = module {
	single<IDocumentsListRepository> { DocumentsListRepositoryImpl(get()) }
}