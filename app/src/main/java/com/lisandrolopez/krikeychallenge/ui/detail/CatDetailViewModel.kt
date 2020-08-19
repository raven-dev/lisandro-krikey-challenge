package com.lisandrolopez.krikeychallenge.ui.detail

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lisandrolopez.krikeychallenge.repository.model.Cat

class CatDetailViewModel() : ViewModel() {

    var cat: Cat? = null
    val titleEvent = MutableLiveData<String>()

    fun setDetailCat(detailCat: Cat) {
        cat = detailCat
        cat?.breeds?.getOrNull(0)?.name?.let {
            titleEvent.value = it
        }
    }

    fun getCatImageUrl(): String {
        return cat?.url ?: DEFAULT_CAT_URL
    }

    fun getDescription(): String {
        return cat?.breeds?.getOrNull(0)?.description ?: ""
    }

    fun getShareIntent() = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, getCatImageUrl())
        type = "text/plain"
    }

    companion object {
        private const val DEFAULT_CAT_URL = "https://i.kym-cdn.com/entries/icons/original/000/026/489/crying.jpg"
    }
}
