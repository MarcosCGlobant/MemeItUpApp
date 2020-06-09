package com.example.memeitupapp.memedetailtest.memedialogmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.globant.domain.entity.MemeDetail
import com.example.memeitupapp.ui.contract.MemesDetailsContract
import com.example.memeitupapp.ui.memedetail.model.MemeDetailModel
import com.globant.domain.service.MemeService
import com.globant.domain.util.Result
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MemeDetailModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val memeService: MemeService = mock()
    private lateinit var memeDetailModel: MemesDetailsContract.Model

    @Before
    fun setup() {
        memeDetailModel = MemeDetailModel(memeService)
    }

    @Test
    fun `on get meme details successfully`() {
        val mockedMemeResult: Result.Success<MemeDetail> = mock()
        whenever(memeService.getMemeByIdFromApi(MEME_ID)).thenReturn(mockedMemeResult)

        runBlocking { memeDetailModel.getMeme(MEME_ID) }

        assertEquals(mockedMemeResult, memeDetailModel.getMeme(MEME_ID))
    }

    @Test
    fun `on get meme details with error connection`() {
        val mockedMemeResult: Result.Failure = mock()
        whenever(memeService.getMemeByIdFromApi(MEME_ID)).thenReturn(mockedMemeResult)

        runBlocking { memeDetailModel.getMeme(MEME_ID) }

        assertEquals(mockedMemeResult, memeDetailModel.getMeme(MEME_ID))
    }

    companion object {
        const val MEME_ID = 10
    }
}