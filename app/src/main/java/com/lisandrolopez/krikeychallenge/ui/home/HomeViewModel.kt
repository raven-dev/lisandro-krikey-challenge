package com.lisandrolopez.krikeychallenge.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lisandrolopez.krikeychallenge.repository.CatRepository
import com.lisandrolopez.krikeychallenge.repository.model.Cat
import com.lisandrolopez.krikeychallenge.repository.network.util.Resource
import kotlinx.coroutines.launch

class HomeViewModel(private val catRepository: CatRepository) : ViewModel() {

    val catSelected = MutableLiveData<Cat>()
    val kittiesEvent = MutableLiveData<Resource<List<Cat>?>>()
    val isLoadingEvent = MutableLiveData<Boolean>()
    private var currentSearch: String? = null

    fun getCatList() {
        viewModelScope.launch {
            val result = catRepository.getKitties()
            kittiesEvent.value = result
            isLoadingEvent.value = false
        }
    }

    fun search(query: String?) {
        if (currentSearch != query) {
            currentSearch = query
            isLoadingEvent.value = true
            query?.let {
                viewModelScope.launch {
                    val result = catRepository.searchKitties(it)
                    kittiesEvent.value = result
                    isLoadingEvent.value = false
                }
            }
        }
    }

    class Factory(private val catRepository: CatRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(catRepository) as T
        }

    }

    fun catToShow(cat: Cat) {
        catSelected.value = cat
    }
}
