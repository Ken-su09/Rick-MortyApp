package com.suonk.rickmortyapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suonk.rickmortyapp.models.data.CharactersApiResponse
import com.suonk.rickmortyapp.models.data.Result
import com.suonk.rickmortyapp.repositories.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCharactersViewModel @Inject constructor(private val repository: DefaultRepository) :
    ViewModel() {

    val allCharactersLiveData = MutableLiveData<CharactersApiResponse>()
    val characterSelectedLiveData = MutableLiveData<Result>()
    val searchBarText = MutableLiveData<String>()

    var pageNumber = 1
    var characterResponse: CharactersApiResponse? = null

    fun getAllCharacters() = viewModelScope.launch {
        val response = repository.getAllCharacters(pageNumber.toString())
        if (response.isSuccessful) {
            pageNumber += 1
            if (characterResponse == null) {
                characterResponse = response.body()
            } else {
                val oldCharacters = characterResponse?.results
                val newCharacters = response.body()?.results
                newCharacters?.let { oldCharacters?.addAll(it) }
            }
            allCharactersLiveData.postValue(characterResponse ?: response.body())
        }
    }

    fun setCharacterSelected(character: Result) {
        characterSelectedLiveData.postValue(character)
    }

    fun setSearchText(text: String) {
        searchBarText.postValue(text)
    }
}