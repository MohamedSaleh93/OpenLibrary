package com.mohamed.opendocumentlibrary.usecase

import com.mohamed.opendocumentlibrary.model.CallResult
import com.mohamed.opendocumentlibrary.model.Document
import com.mohamed.opendocumentlibrary.repository.IDocumentsListRepository
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
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class GetDocumentsUseCaseTest : AutoCloseKoinTest() {
	private val subject: IGetDocumentsUseCase by inject()
	private val mockedResponse: Response<CallResult> = mockk()
	private val documentsListRepository: IDocumentsListRepository = mockk {
		every {
			runBlocking {
				requestDocuments(any())
			}
		} returns mockedResponse
	}

	@Before
	fun setup() {
		val module = module {
			single { documentsListRepository }
			single<IGetDocumentsUseCase> { GetDocumentsUseCase(get()) }
		}

		startKoin {
			modules(listOf(module))
		}
	}

	@Test
	fun `Test calling documents list repository is calling the right method`() = runBlocking {
		// Act
		subject.getDocumentsResultFromQuery("test")

		// assert
		coVerify(
			verifyBlock = {
				documentsListRepository.requestDocuments("test")
			}
		, atLeast = 1)
	}

	@Test
	fun `Test get documents result return valid data in normal scenario`() = runBlocking {
		// Mock
		every {
			mockedResponse.isSuccessful
		} returns true

		every {
			mockedResponse.body()
		} returns getMockedCallResultWithDocuments()

		// Act
		val result = subject.getDocumentsResultFromQuery("test")

		// Assertion
		assertTrue(result.documentsList!!.isNotEmpty())
		assertNull(result.exception)
	}

	@Test
	fun `Test get documents handle the exceptions when request is not success`() = runBlocking {
		// Mock
		every {
			mockedResponse.isSuccessful
		} returns false

		every {
			mockedResponse.errorBody()
		} returns mockk()

		// Act
		val result = subject.getDocumentsResultFromQuery("test")

		// Assert
		assertNotNull(result.exception)
		assertNull(result.documentsList)
	}

	@Test
	fun `Test get documents handle any general exception happened during the call`() = runBlocking {
		// Mock
		every {
			runBlocking {
				documentsListRepository.requestDocuments("test")
			}
		} throws Exception("Error")

		// Act
		val result = subject.getDocumentsResultFromQuery("test")

		// Assert
		assertNotNull(result.exception)
		assertNull(result.documentsList)
		assertEquals(result.exception?.message, "Error")
	}

	private fun getMockedCallResultWithDocuments(): CallResult {
		val isbn = listOf("123", "65656")
		val isbn2 = listOf("7346", "5335")
		val author = listOf("Name1")
		val author2 = listOf("Name2")
		val documentsList = listOf(Document("Lord of the rings", isbn, author),
		Document("The hobbit", isbn2, author2))
		return CallResult(documentsList)
	}
}