package com.example.kimkazandi.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kimkazandi.Results
import com.example.kimkazandi.model.Detail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel : ViewModel() {
    private val _Detail = MutableLiveData<Detail>()
    val _myDetail: LiveData<Detail> = _Detail


    fun getDetail(url: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                Results().getDetail(url)
            }
            _Detail.value = result
        }

}}