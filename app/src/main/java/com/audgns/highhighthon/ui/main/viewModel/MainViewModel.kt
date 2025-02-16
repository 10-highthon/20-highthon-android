package com.audgns.highhighthon.ui.main.viewModel

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.audgns.highhighthon.model.ChannelResponse
import com.audgns.highhighthon.model.DeleteFavoriteModel
import com.audgns.highhighthon.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val apiService = RetrofitClient.apiService

    private val _mainUiState = MutableStateFlow(MainUiState(ChannelResponse(emptyList(), 0)))
    internal val mainUiState = _mainUiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    internal val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    internal fun getFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                apiService.getFavorite()
            }.onSuccess { response ->
                _mainUiState.value = _mainUiState.value.copy(mainList = response)
                _isRefreshing.value = false

            }.onFailure {
                Log.e("Fail", "Failed GetFavorite Function")
            }.also {
                _isRefreshing.value = false
            }
        }
    }

    internal fun deleteFavorite(body: DeleteFavoriteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                apiService.deleteFavorite(body = body)
            }.onSuccess {
                Log.e("Success", "Success DeleteFavorite Function")
                getFavorite()
            }.onFailure { error ->
                Log.e("Fail", "$error")
                getFavorite()
            }
        }
    }
}

data class MainUiState(
    val mainList: ChannelResponse
)