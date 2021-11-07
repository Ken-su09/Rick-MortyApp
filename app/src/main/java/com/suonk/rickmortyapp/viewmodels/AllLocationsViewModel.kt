package com.suonk.rickmortyapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suonk.rickmortyapp.models.data.CharactersApiResponse
import com.suonk.rickmortyapp.repositories.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllLocationsViewModel @Inject constructor(private val repository: DefaultRepository) : ViewModel() {

    val allLocationsLiveData = MutableLiveData<CharactersApiResponse>()

    fun getAllLocations(page: String?) = viewModelScope.launch {
        val response = repository.getAllLocations(page)
        if(response.isSuccessful){
            allLocationsLiveData.postValue(response.body())
        }
    }
}