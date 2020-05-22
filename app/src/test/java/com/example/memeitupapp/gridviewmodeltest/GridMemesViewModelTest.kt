package com.example.memeitupapp.gridviewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.memeitupapp.data.entity.Meme
import com.example.memeitupapp.ui.contract.GridMemesContract
import com.example.memeitupapp.ui.gridmemes.viewmodel.GridMemesViewModel
import com.example.memeitupapp.util.Result
import com.example.memeitupapp.util.Status
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GridMemesViewModelTest {

    @ObsoleteCoroutinesApi
    private var mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: GridMemesContract.ViewModel
    private var memes: List<Meme> = mock()
    private val mockedGridMemesModel: GridMemesContract.Model = mock()

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)

        subject = GridMemesViewModel(mockedGridMemesModel)
    }

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @After
    fun after() {
        mainThreadSurrogate.close()
        Dispatchers.resetMain()
    }

    @Test
    fun `on loading grid of memes successfully`() {
        val mockedMemesResult: Result.Success<List<Meme>> = mock()
        val liveDataUnderTest = subject.getLiveData().testObserver()
        whenever(mockedGridMemesModel.getMemes()).thenReturn(mockedMemesResult)
        whenever(mockedMemesResult.data).thenReturn(memes)

        runBlocking {
            subject.fetchMemes().join()
        }
        assertEquals(Status.LOADING, liveDataUnderTest.observedValues[FIRST_RESPONSE]?.peekContent()?.responseType)
        assertEquals(Status.GET_MEMES_SUCCESS, liveDataUnderTest.observedValues[SECOND_RESPONSE]?.peekContent()?.responseType)
        assertNotNull(liveDataUnderTest.observedValues[SECOND_RESPONSE]?.peekContent()?.data)
    }

    @Test
    fun `on loading grid of memes with error connection`() {
        val mockedMemesResult: Result.Failure = mock()
        val liveDataUnderTest = subject.getLiveData().testObserver()
        whenever(mockedGridMemesModel.getMemes()).thenReturn(mockedMemesResult)

        runBlocking {
            subject.fetchMemes().join()
        }
        assertEquals(Status.LOADING, liveDataUnderTest.observedValues[FIRST_RESPONSE]?.peekContent()?.responseType)
        assertEquals(Status.GET_MEMES_ERROR, liveDataUnderTest.observedValues[SECOND_RESPONSE]?.peekContent()?.responseType)
        assertNull(liveDataUnderTest.observedValues[SECOND_RESPONSE]?.peekContent()?.error)
    }

    class TestObserver<T> : Observer<T> {
        val observedValues = mutableListOf<T?>()
        override fun onChanged(value: T?) {
            observedValues.add(value)
        }
    }

    private fun <T> LiveData<T>.testObserver() = TestObserver<T>().also { observeForever(it) }

    companion object {
        const val FIRST_RESPONSE = 0
        const val SECOND_RESPONSE = 1
    }
}