package com.mohamed.opendocumentlibrary.viewmodel

import com.mohamed.opendocumentlibrary.model.Document
import com.mohamed.opendocumentlibrary.model.RequestDocumentResult
import com.mohamed.opendocumentlibrary.usecase.IGetDocumentsUseCase
import com.mohamed.opendocumentlibrary.viewstate.DocumentsDataListResult
import com.mohamed.opendocumentlibrary.viewstate.DocumentsListViewState
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import kotlin.test.assertTrue
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mohamed.opendocumentlibrary.viewstate.ErrorState
import com.mohamed.opendocumentlibrary.viewstate.LoadingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import java.lang.Exception

class MainViewModelTest : AutoCloseKoinTest() {
	private val subject: MainViewModel by inject()
	private val getDocumentUseCase: IGetDocumentsUseCase = mockk()

	@get:Rule
	val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

	@ExperimentalCoroutinesApi
	@Before
	fun setup() {
		val module = module {
			single { getDocumentUseCase }
			viewModel { MainViewModel(get()) }
		}

		startKoin {
			modules(listOf(module))
		}
		Dispatchers.setMain(StandardTestDispatcher())
	}

	@ExperimentalCoroutinesApi
	@Test
	fun `Test starting call get document use case change the state to LoadingState`() {
		// Mock
		every {
			runBlocking {
				getDocumentUseCase.getDocumentsResultFromQuery("test")
			}
		} returns mockRequestDocumentResult()
		runTest {
			// Act
			subject.getDocumentList("test")

			// Assert
			assertTrue(subject.documentsListState.value is LoadingState)
		}
	}

	@ExperimentalCoroutinesApi
	@Test
	fun `Test call get document use case return when success the state is changed to DocumentsDataListResult`() {
		// Mock
		every {
			runBlocking {
				getDocumentUseCase.getDocumentsResultFromQuery("test")
			}
		} returns mockRequestDocumentResult()

		runTest {
			// Act
			subject.getDocumentList("test")

		}

		// Assert
		assertTrue(subject.documentsListState.value is DocumentsDataListResult)
	}

	@ExperimentalCoroutinesApi
	@Test
	fun `Test call get document use case when return error the state is changed to ErrorState`() {
		// Mock
		every {
			runBlocking {
				getDocumentUseCase.getDocumentsResultFromQuery("test")
			}
		} returns RequestDocumentResult(exception = Exception("Error"))

		runTest {
			// Act
			subject.getDocumentList("test")

		}

		// Assert
		assertTrue(subject.documentsListState.value is ErrorState)
	}

	private fun mockRequestDocumentResult(): RequestDocumentResult {
		val isbn = listOf("123", "65656")
		val isbn2 = listOf("7346", "5335")
		val author = listOf("Name1")
		val author2 = listOf("Name2")
		val documentsList = listOf(
			Document("Lord of the rings", isbn, author),
			Document("The hobbit", isbn2, author2)
		)
		return RequestDocumentResult(documentsList = documentsList)
	}
}