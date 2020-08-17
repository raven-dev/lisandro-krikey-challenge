package com.lisandrolopez.krikeychallenge.ui.home

import androidx.lifecycle.*
import com.lisandrolopez.krikeychallenge.repository.CatRepository

class HomeViewModel(private val catRepository: CatRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val kittiesEvent = liveData {
        val result = catRepository.getKitties()
        emit(result)
    }

    class Factory(private val catRepository: CatRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(catRepository) as T
        }

    }
}
