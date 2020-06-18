package com.example.memeitupapp.memedetailtest.memedialogmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.globant.domain.entity.MemeDetail
import com.example.memeitupapp.ui.contract.MemesDetailsContract
import com.example.memeitupapp.ui.memedetail.model.MemeDetailModel
import com.globant.domain.usecase.GetMemeByIdFromDataBaseUseCase
import com.globant.domain.usecase.GetMemeByIdUseCase
import com.globant.domain.usecase.UpdateMemeDetailsDataBaseUseCase
import com.globant.domain.util.Result
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MemeDetailModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockedGetMemeByIdUseCase: GetMemeByIdUseCase = mock()
    private val mockedGetMemeByIdFromDataBaseUseCase: GetMemeByIdFromDataBaseUseCase = mock()
    private val mockedUpdateMemeDetailsDataBaseUseCase: UpdateMemeDetailsDataBaseUseCase = mock()
    private lateinit var memeDetailModel: MemesDetailsContract.Model

    @Before
    fun setup() {
        memeDetailModel = MemeDetailModel(mockedGetMemeByIdUseCase, mockedUpdateMemeDetailsDataBaseUseCase, mockedGetMemeByIdFromDataBaseUseCase)
    }

    @Test
    fun `on GetMemeByIdUseCase gets data from API, updates data base`() {
        val mockedMemeResult: Result.Success<MemeDetail> = mock()
        whenever(mockedGetMemeByIdUseCase.invoke(MEME_ID)).thenReturn(mockedMemeResult)

        assertEquals(mockedMemeResult, memeDetailModel.getMeme(MEME_ID))

        verify(mockedGetMemeByIdUseCase).invoke(MEME_ID)
        verify(mockedUpdateMemeDetailsDataBaseUseCase).invoke(mockedMemeResult.data)
    }

    @Test
    fun `on GetMemeByIdUseCase fails, invoke GetMemeByIdFromDataBase`() {
        val mockedMemeDetailResultFailure: Result.Failure = mock()
        val mockedMemeDetailResultSuccess: Result.Success<MemeDetail> = mock()
        whenever(mockedGetMemeByIdUseCase.invoke(MEME_ID)).thenReturn(mockedMemeDetailResultFailure)
        whenever(mockedGetMemeByIdFromDataBaseUseCase.invoke(MEME_ID)).thenReturn(mockedMemeDetailResultSuccess)

        assertEquals(mockedMemeDetailResultSuccess, memeDetailModel.getMeme(MEME_ID))

        verify(mockedGetMemeByIdUseCase).invoke(MEME_ID)
        verify(mockedGetMemeByIdFromDataBaseUseCase).invoke(MEME_ID)
    }

    companion object {
        const val MEME_ID = 10
    }
}