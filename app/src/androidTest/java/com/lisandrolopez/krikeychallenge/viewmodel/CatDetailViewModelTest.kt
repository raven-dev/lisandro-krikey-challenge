package com.lisandrolopez.krikeychallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lisandrolopez.krikeychallenge.repository.model.Cat
import com.lisandrolopez.krikeychallenge.ui.detail.CatDetailViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CatDetailViewModelTest {
    private lateinit var viewModel: CatDetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = CatDetailViewModel()
        viewModel.cat = Cat("0", "https://foo.com/some", 1024, 1024, null)

    }

    @Test
    @Throws(InterruptedException::class)
    fun checkCat() {
        assert(viewModel.cat != null)
        assert(viewModel.cat?.id != null)
        assert(viewModel.cat?.width == 1024)
        assert(viewModel.cat?.height == 1024)
        assert(viewModel.cat?.breeds == null)
    }

}
