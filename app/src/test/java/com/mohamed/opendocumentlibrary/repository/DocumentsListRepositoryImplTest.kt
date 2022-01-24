package com.mohamed.opendocumentlibrary.repository

import com.mohamed.opendocumentlibrary.datasource.IDocumentsListDataSource
import com.mohamed.opendocumentlibrary.model.CallResult
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import retrofit2.Response
import kotlin.test.assertEquals

class DocumentsListRepositoryImplTest : AutoCloseKoinTest() {
	private val subject: IDocumentsListRepository by inject()
	private val mockedCallResponse: Response<CallResult> = mockk()
	private val documentsListDatasource: IDocumentsListDataSource = mockk {
		every {
			runBlocking {
				requestDocuments(any())
			}
		} returns mockedCallResponse
	}

	@Before
	fun setup() {
		val module = module {
			single { documentsListDatasource }
			single<IDocumentsListRepository> { DocumentsListRepositoryImpl(get()) }
		}
		startKoin {
			modules(listOf(module))
		}
	}

	@Test
	fun `Test calling documents remote datasource is calling the right method`() = runBlocking {
		// Act
		subject.requestDocuments("test")

		// assert
		coVerify(
			verifyBlock = {
				documentsListDatasource.requestDocuments("test")
			}
		, atLeast = 1)
	}

	@Test
	fun `Test calling documents remote datasource return the expected value`() = runBlocking {
		// Act
		val result = subject.requestDocuments("test")

		// assert
		assertEquals(result, mockedCallResponse)
	}
}