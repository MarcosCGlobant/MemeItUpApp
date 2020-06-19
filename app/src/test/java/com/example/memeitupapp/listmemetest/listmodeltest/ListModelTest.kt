package com.example.memeitupapp.listmemetest.listmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.globant.domain.entity.Meme
import com.example.memeitupapp.ui.contract.ListMemesContract
import com.example.memeitupapp.ui.listmemes.model.ListMemesModel
import com.globant.domain.usecase.GetMemesFromDataBaseUseCase
import com.globant.domain.usecase.GetMemesUseCase
import com.globant.domain.usecase.UpdateMemesDataBaseUseCase
import com.globant.domain.util.Result
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ListModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockedGetMemesUseCase: GetMemesUseCase = mock()
    private val mockedGetMemesFromDataBaseUseCase: GetMemesFromDataBaseUseCase = mock()
    private val mockedUpdateMemesDataBaseUseCase: UpdateMemesDataBaseUseCase = mock()
    private val mockedMemesResult: Result.Success<List<Meme>> = mock()
    private lateinit var listMemesModel: ListMemesContract.Model

    @Before
    fun setup() {
        listMemesModel = ListMemesModel(mockedGetMemesUseCase, mockedUpdateMemesDataBaseUseCase, mockedGetMemesFromDataBaseUseCase)
    }

    @Test
    fun `on GetMemesUseCase gets data from API, update data base`() {
        whenever(mockedGetMemesUseCase.invoke()).thenReturn(mockedMemesResult)

        assertEquals(mockedMemesResult, listMemesModel.getMemes())

        verify(mockedGetMemesUseCase).invoke()
        verify(mockedUpdateMemesDataBaseUseCase).invoke(mockedMemesResult.data)
    }

    @Test
    fun `on GetMemesUseCase fails, invoke GetMemesFromDataBaseUseCase`() {
        whenever(mockedGetMemesFromDataBaseUseCase.invoke()).thenReturn(mockedMemesResult)

        assertEquals(mockedMemesResult, listMemesModel.getMemes())

        verify(mockedGetMemesUseCase).invoke()
        verify(mockedGetMemesFromDataBaseUseCase).invoke()
    }
}
