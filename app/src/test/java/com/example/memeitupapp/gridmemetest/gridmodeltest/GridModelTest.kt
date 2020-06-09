package com.example.memeitupapp.gridmemetest.gridmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.globant.domain.entity.Meme
import com.globant.data.service.MemeService
import com.example.memeitupapp.ui.contract.GridMemesContract
import com.example.memeitupapp.ui.gridmemes.model.GridMemesModel
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
class GridModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val memeService: com.globant.data.service.MemeService = mock()
    private lateinit var gridMemesModel: GridMemesContract.Model

    @Before
    fun setup() {
        gridMemesModel = GridMemesModel(memeService)
    }

    @Test
    fun `on get memes for grid successfully`() {
        val mockedMemesResult: Result.Success<List<Meme>> = mock()
        whenever(memeService.getMemesFromApi()).thenReturn(mockedMemesResult)

        runBlocking { gridMemesModel.getMemes() }

        assertEquals(mockedMemesResult, gridMemesModel.getMemes())
    }

    @Test
    fun `on get memes for grid with error connection`() {
        val mockedMemesResult: Result.Failure = mock()
        whenever(memeService.getMemesFromApi()).thenReturn(mockedMemesResult)

        runBlocking { gridMemesModel.getMemes() }

        assertEquals(mockedMemesResult, gridMemesModel.getMemes())
    }
}