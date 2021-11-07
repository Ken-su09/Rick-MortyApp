package com.suonk.rickmortyapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suonk.rickmortyapp.models.data.ApiResponse
import com.suonk.rickmortyapp.models.data.Result
import com.suonk.rickmortyapp.repositories.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCharactersViewModel @Inject constructor(private val repository: DefaultRepository) :
    ViewModel() {

    val allCharactersLiveData = MutableLiveData<ApiResponse>()
    val characterSelectedLiveData = MutableLiveData<Result>()

    fun getAllCharacters(id: Int?, page: String?) = viewModelScope.launch {
        val response = repository.getAllCharacters(id, page)
        if (response.isSuccessful) {
            allCharactersLiveData.postValue(response.body())
        }
    }

    fun setCharacterSelected(character: Result) {
        characterSelectedLiveData.postValue(character)
    }
}