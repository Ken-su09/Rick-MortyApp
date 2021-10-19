package com.suonk.rickmortyapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suonk.rickmortyapp.models.data.ApiResponse
import com.suonk.rickmortyapp.repositories.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DefaultRepository) : ViewModel() {

    val allCharactersLiveData = MutableLiveData<ApiResponse>()
    val allLocationsLiveData = MutableLiveData<ApiResponse>()
    val allEpisodesLiveData = MutableLiveData<ApiResponse>()

    fun getAllCharacters(id: Int?, page: String?) = viewModelScope.launch {
        val response = repository.getAllCharacters(id, page)
        if(response.isSuccessful){
            allLocationsLiveData.postValue(response.body())
        }
    }

    fun getAllLocations(id: Int?, page: String?) = viewModelScope.launch {
        val response = repository.getAllLocations(id, page)
        if(response.isSuccessful){
            allCharactersLiveData.postValue(response.body())
        }
    }

    fun getAllEpisodes(id: Int?, page: String?) = viewModelScope.launch {
        val response = repository.getAllEpisodes(id, page)
        if(response.isSuccessful){
            allEpisodesLiveData.postValue(response.body())
        }
    }
}