package com.example.memeitupapp.memedetailtest.memedialogviewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.globant.domain.entity.MemeDetail
import com.example.memeitupapp.ui.contract.MemesDetailsContract
import com.example.memeitupapp.ui.memedetail.viewmodel.MemeDetailViewModel
import com.globant.domain.util.Result
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
class MemeDetailViewModelTest {

    @ObsoleteCoroutinesApi
    private var mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: MemesDetailsContract.ViewModel
    private var meme: MemeDetail = mock()
    private val mockedMemeDetailModel: MemesDetailsContract.Model = mock()

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)

        subject = MemeDetailViewModel(mockedMemeDetailModel)
    }

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @After
    fun after() {
        mainThreadSurrogate.close()
        Dispatchers.resetMain()
    }

    @Test
    fun `on loading meme details successfully`() {
        val mockedMemeResult: Result.Success<MemeDetail> = mock()
        val liveDataUnderTest = subject.getLiveData().testObserver()
        whenever(mockedMemeDetailModel.getMeme(MEME_ID)).thenReturn(mockedMemeResult)
        whenever(mockedMemeResult.data).thenReturn(meme)

        runBlocking {
            subject.fetchMeme(MEME_ID).join()
        }
        assertEquals(Status.LOADING, liveDataUnderTest.observedValues[FIRST_RESPONSE]?.peekContent()?.responseType)
        assertEquals(Status.GET_MEME_BY_ID_SUCCESS, liveDataUnderTest.observedValues[SECOND_RESPONSE]?.peekContent()?.responseType)
        assertNotNull(liveDataUnderTest.observedValues[SECOND_RESPONSE]?.peekContent()?.data)
    }

    @Test
    fun `on loading meme details with error connection`() {
        val mockedMemeResult: Result.Failure = mock()
        val liveDataUnderTest = subject.getLiveData().testObserver()
        whenever(mockedMemeDetailModel.getMeme(MEME_ID)).thenReturn(mockedMemeResult)

        runBlocking {
            subject.fetchMeme(MEME_ID).join()
        }
        assertEquals(Status.LOADING, liveDataUnderTest.observedValues[FIRST_RESPONSE]?.peekContent()?.responseType)
        assertEquals(Status.GET_MEME_BY_ID_ERROR, liveDataUnderTest.observedValues[SECOND_RESPONSE]?.peekContent()?.responseType)
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
        const val MEME_ID = 10
        const val FIRST_RESPONSE = 0
        const val SECOND_RESPONSE = 1
    }
}