package com.example.protapptest.ui.viewmodels

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.PendingActions
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.protapptest.data.local.dto.NavigationItem
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.domain.use_cases.auth.AuthUseCases
import com.example.protapptest.domain.use_cases.producer.ProducerUseCases
import com.example.protapptest.security.TokenManager
import com.example.protapptest.ui.events.HomeEvent
import com.example.protapptest.ui.events.SplashEvent
import com.example.protapptest.ui.navigation.Screen
import com.example.protapptest.ui.states.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val producerUseCases: ProducerUseCases,
    private val tokenManager: TokenManager,
    private val authViewModel: AuthViewModel,
    private val signInViewModel: SignInViewModel
) : ViewModel() {

    var state by mutableStateOf(HomeState())

    private val _signOutResponse: MutableState<ApiResponse<MessageResponse>> =
        mutableStateOf(ApiResponse.Loading)
    val signOutResponse = _signOutResponse


    var signOutJob: Job? = null
    var getProducerJob: Job? = null

    init {
        Log.d("HomeViewModel", " init ")
        getProducer()
    }

    val bottomNavigationItemsList = listOf(
        NavigationItem(
            title = "Inicio",
            icon = Icons.Default.Home,
            description = "Home Screen",
            itemId = Screen.ProductsScreen.route,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        NavigationItem(
            title = "Direcciones",
            icon = Icons.Default.Map,
            description = "Mis direcciones",
            itemId = Screen.AddressListScreen.route,
            selectedIcon = Icons.Filled.Map,
            unselectedIcon = Icons.Outlined.Map
        ),
        NavigationItem(
            title = "Pendientes",
            icon = Icons.Default.PendingActions,
            description = "Muestra los pedidos pendientes del productor",
            itemId = Screen.PendingOrdersScreen.route, // TODO
            selectedIcon = Icons.Filled.PendingActions,
            unselectedIcon = Icons.Outlined.PendingActions
        ),
        NavigationItem(
            title = "Notificaciones",
            icon = Icons.Default.Notifications,
            description = "Mis direcciones",
            itemId = Screen.NotificationsScreen.route, // TODO
            selectedIcon = Icons.Filled.Notifications,
            unselectedIcon = Icons.Outlined.Notifications
        ),
    )

    val navigationItemsList = listOf(
        NavigationItem(
            title = "Inicio",
            icon = Icons.Default.Home,
            description = "Home Screen",
            itemId = Screen.HomeScreen.route
        ),
        NavigationItem(
            title = "Mis pedidos",
            icon = Icons.Default.Person,
            description = "Favorite Screen",
            itemId = Screen.OrderListScreen.route
        ),
        NavigationItem(
            title = "Configuracion",
            icon = Icons.Default.Settings,
            description = "Settings Screen",
            itemId = Screen.SettingsScreen.route
        ),
        NavigationItem(
            title = "Direcciones",
            icon = Icons.Default.Map,
            description = "Favorite Screen",
            itemId = Screen.AddressListScreen.route
        ),
    )

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.SignOutBtnClicked -> {
                signOutTest()
            }

            HomeEvent.Refresh -> {
                getProducer(fetchFromRemote = true)
            }
        }
    }


    fun signOutTest() {
        signOutJob = viewModelScope.launch {
            authUseCases.signOut().collect {
                when (it) {
                    ApiResponse.Waiting -> {}
                    ApiResponse.Loading -> {}
                    is ApiResponse.Failure -> {
                        val data = it.errorMessage
                        Log.d("ApiResponse", "errorMessage = $data")
                    }

                    is ApiResponse.Success -> {
                        it.data.let {
                            Log.d("ApiResponse", "message = $it")
                            tokenManager.deleteAccessToken()
                            authViewModel.onEvent(SplashEvent.CheckAuthentication)
                        }
                    }
                }
            }
        }
    }

    fun getProducer(fetchFromRemote: Boolean = false) {
        getProducerJob?.cancel()
        getProducerJob = viewModelScope.launch(Dispatchers.IO) {
            Log.d("HomeViewModel", "getProducer")

            val userId = tokenManager.getUserId().first()

            Log.d("HomeViewModel", "userId = $userId")
            if (userId != null) {
                producerUseCases.getProducer(fetchFromRemote, userId).collect { apiResponse ->
                    when (apiResponse) {
                        ApiResponse.Loading -> {}

                        ApiResponse.Waiting -> {}

                        is ApiResponse.Failure -> {

                        }

                        is ApiResponse.Success -> {

                            apiResponse.data.let {
                                withContext(Dispatchers.Main) {
                                    state = state.copy(
                                        producerInfoResponse = it
                                    )
                                }
                            }

                            Log.d(
                                "HomeViewModel",
                                "getProducer | Success | state.value = ${state} "
                            )
                        }


                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        signOutJob?.let {
            if (it.isActive) {
                it.cancel()
            }
        }

        getProducerJob?.let {
            if (it.isActive) {
                it.cancel()
            }
        }
    }
}
