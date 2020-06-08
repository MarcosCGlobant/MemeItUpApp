package com.example.memeitupapp.listmemetest.listmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.globant.domain.entity.Meme
import com.example.memeitupapp.data.repository.MemeService
import com.example.memeitupapp.ui.contract.ListMemesContract
import com.example.memeitupapp.ui.listmemes.model.ListMemesModel
import com.globant.domain.util.Result
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
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

    private val memeService: MemeService = mock()
    private lateinit var listMemesModel: ListMemesContract.Model

    @Before
    fun setup() {
        listMemesModel = ListMemesModel(memeService)
    }

    @Test
    fun `on get memes for list successfully`() {
        val mockedMemesResult: Result.Success<List<Meme>> = mock()
        whenever(memeService.getMemesFromApi()).thenReturn(mockedMemesResult)

        runBlocking { listMemesModel.getMemes() }

        assertEquals(mockedMemesResult, listMemesModel.getMemes())
    }

    @Test
    fun `on get memes for list with error connection`() {
        val mockedMemesResult: Result.Failure = mock()
        whenever(memeService.getMemesFromApi()).thenReturn(mockedMemesResult)

        runBlocking { listMemesModel.getMemes() }

        assertEquals(mockedMemesResult, listMemesModel.getMemes())
    }
}