package com.example.memeitupapp.gridmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.memeitupapp.data.entity.Meme
import com.example.memeitupapp.data.repository.MemeService
import com.example.memeitupapp.ui.contract.GridMemesContract
import com.example.memeitupapp.ui.gridmemes.model.GridMemesModel
import com.example.memeitupapp.util.Result
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GridModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val memeService: MemeService = mock()
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