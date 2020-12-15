package com.kaundinyakasibhatla.trainman_assignment.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.kaundinyakasibhatla.trainman_assignment.BuildConfig
import com.kaundinyakasibhatla.trainman_assignment.data.model.Gif
import com.kaundinyakasibhatla.trainman_assignment.data.repository.MainRepository
import com.kaundinyakasibhatla.trainman_assignment.utils.NetworkHelper
import com.kaundinyakasibhatla.trainman_assignment.utils.Resource
import kotlinx.coroutines.launch


class MainActivityViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _gifs = MutableLiveData<Resource<List<String>>>()
    val gifs: LiveData<Resource<List<String>>>
        get() = _gifs

    fun fetchGifs(offset: Int) {

        viewModelScope.launch {
            if (offset == 0) {
                _gifs.postValue(Resource.loading(emptyList()))
            } else {
                _gifs.postValue(Resource.loading(gifs.value?.data))
            }
            if (networkHelper.isNetworkConnected()) {

                val response = mainRepository.getTrendingGifs(BuildConfig.api_key, 25, offset)
                if (response.isSuccessful) {
                    response.body()?.let {


                        val gifsList = it.data?.mapNotNull { gif ->
                            gif.images?.original?.url
                        }

                        _gifs.postValue(Resource.success(gifs.value?.data!! + gifsList!! ))


                    } ?: run {
                        _gifs.postValue(Resource.error(response.errorBody().toString(), null))
                    }

                } else {
                    _gifs.postValue(Resource.error(response.errorBody().toString(), null))
                }

            } else _gifs.postValue(Resource.error("No internet connection", null))
        }
    }


}

