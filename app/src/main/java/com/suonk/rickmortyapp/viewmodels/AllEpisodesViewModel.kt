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
class AllEpisodesViewModel @Inject constructor(private val repository: DefaultRepository) : ViewModel() {

    val allEpisodesLiveData = MutableLiveData<ApiResponse>()

    fun getAllEpisodes(id: Int?, page: String?) = viewModelScope.launch {
        val response = repository.getAllEpisodes(id, page)
        if(response.isSuccessful){
            allEpisodesLiveData.postValue(response.body())
        }
    }
}