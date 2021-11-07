package com.suonk.rickmortyapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suonk.rickmortyapp.models.data.CharactersApiResponse
import com.suonk.rickmortyapp.models.data.EpisodesApiResponse
import com.suonk.rickmortyapp.repositories.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllEpisodesViewModel @Inject constructor(private val repository: DefaultRepository) : ViewModel() {

    val allEpisodesLiveData = MutableLiveData<EpisodesApiResponse>()

    fun getAllEpisodes(page: String?) = viewModelScope.launch {
        val response = repository.getAllEpisodes(page)
        if(response.isSuccessful){
            allEpisodesLiveData.postValue(response.body())
        }
    }
}