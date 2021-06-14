package com.example.uaenewsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.uaenewsapp.data.api.NewApiRepo
import com.example.uaenewsapp.data.api.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NewApiRepo
) : ViewModel() {

    val news = repository.getNews().asLiveData()

    fun onlineData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.onlineData()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
        companion object {
            fun <T> success(data: T): Resource<T> =
                Resource(status = Status.SUCCESS, data = data, message = null)

            fun <T> error(data: T?, message: String): Resource<T> =
                Resource(status = Status.ERROR, data = data, message = message)

            fun <T> loading(data: T?): Resource<T> =
                Resource(status = Status.LOADING, data = data, message = null)
        }
    }
}