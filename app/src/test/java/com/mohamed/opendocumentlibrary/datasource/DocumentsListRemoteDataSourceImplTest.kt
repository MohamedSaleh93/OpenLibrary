package com.mohamed.opendocumentlibrary.datasource

import com.mohamed.opendocumentlibrary.model.CallResult
import com.mohamed.opendocumentlibrary.network.DocumentsApiService
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

class DocumentsListRemoteDataSourceImplTest : AutoCloseKoinTest() {
	private val subject: IDocumentsListDataSource by inject()
	private val mockedCallResponse: Response<CallResult> = mockk()
	private val documentsApiService: DocumentsApiService = mockk {
		every {
			runBlocking {
				getDocumentsList(any())
			}
		} returns mockedCallResponse
	}

	@Before
	fun setup() {
		val module = module {
			single { documentsApiService }
			single<IDocumentsListDataSource> { DocumentsListRemoteDataSourceImpl(get()) }
		}
		startKoin {
			modules(listOf(module))
		}
	}

	@Test
	fun `Test calling documents list api service is calling the right method`() = runBlocking {
		// Act
		subject.requestDocuments("test")

		// assert
		coVerify(verifyBlock =  {
			documentsApiService.getDocumentsList("test")
		}, atLeast = 1)
	}

	@Test
	fun `Test calling documents list api return the expected value`() = runBlocking {
		// Act
		val result = subject.requestDocuments("test")

		// assert
		assertEquals(result, mockedCallResponse)
	}
}