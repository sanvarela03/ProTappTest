package com.example.protapptest.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.protapptest.security.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TokenViewModel
@Inject
constructor(
    private val tokenManager: TokenManager
) : ViewModel() {
    private val TAG = TokenViewModel::class.simpleName

    private val _token: MutableStateFlow<String?> = MutableStateFlow(null)
    val token = _token.asStateFlow()

    private val _refreshToken: MutableStateFlow<String?> = MutableStateFlow(null)
    val refreshToken: StateFlow<String?> = _refreshToken

    val isUserSignedIn: MutableLiveData<Boolean> = MutableLiveData(false)

    var job: Job? = null

    init {
        Log.d(TAG, "Starting ... viewModelScope.launch | t :  ${Thread.currentThread().name}")
        job = viewModelScope.launch(Dispatchers.IO) {
            Log.d(
                TAG,
                "Starting ... tokenManager.getAccessToken().collect | t : ${Thread.currentThread().name}"
            )
            tokenManager.getAccessToken().collect {
                Log.d(
                    TAG,
                    "Inside : tokenManager.getAccessToken().collect | it = $it | t : ${Thread.currentThread().name}"
                )
                withContext(Dispatchers.Main) {
                    _token.value = it
                    Log.d(
                        TAG,
                        "Ending ... tokenManager.getAccessToken().collect | t :  ${Thread.currentThread().name}"
                    )
                }
                Log.d(
                    TAG,
                    "Ending ... viewModelScope.launch(Dispatchers.IO) | t :  ${Thread.currentThread().name}"
                )
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            tokenManager.getRefreshToken().collect {
                Log.d(TAG, "refresh token : $it")
            }
        }
    }


    suspend fun saveAccessToken(token: String) {
        Log.d("TokenViewModel: ", "saveAccessToken | token ${token}")
        _token.value = token
        _token.update { token }
        Log.d("TokenViewModel: ", "_token.value: ${_token.value}")
        tokenManager.saveAccessToken(token)

    }

    suspend fun deleteAccessToken() {
        _token.value = null
        tokenManager.deleteAccessToken()
    }

    suspend fun saveRefreshToken(refreshToken: String) {
        _refreshToken.value = refreshToken
        tokenManager.saveRefreshToken(refreshToken)
    }

    suspend fun saveUserId(userId: Long) {

    }

    fun checkForActiveSession() {
        Log.d(TAG, "Starting ... checkForActiveSession()")

        viewModelScope.launch(Dispatchers.Main) {
            val accessToken = tokenManager.getAccessToken().first()
            val refresToken = tokenManager.getRefreshToken().first()

            if (accessToken != null) {

                Log.d(TAG, "Valid Session | access token: $accessToken")
                isUserSignedIn.value = true

            } else {
                Log.d(TAG, "User is not logged in | $accessToken")
                isUserSignedIn.value = false
            }

            Log.d(TAG, "refresh token : $refresToken")
        }
    }
}