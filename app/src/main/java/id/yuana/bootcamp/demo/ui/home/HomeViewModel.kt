package id.yuana.bootcamp.demo.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.yuana.bootcamp.demo.data.repository.AuthRepository
import id.yuana.bootcamp.demo.data.repository.TodoRepository
import id.yuana.bootcamp.demo.ui.UiEffect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val authRepository: AuthRepository,
    private val todoRepository: TodoRepository
) : ViewModel() {

    val homeState: MutableLiveData<HomeState> by lazy {
        MutableLiveData<HomeState>(HomeState())
    }
    val uiEffect: MutableLiveData<UiEffect> by lazy {
        MutableLiveData<UiEffect>()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.OnLogoutClick -> viewModelScope.launch {
                try {
                    authRepository.logout()
                    uiEffect.postValue(
                        UiEffect.Navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
                    )
                } catch (e: Exception) {
                    uiEffect.postValue(
                        UiEffect.ShowToast(
                            e.message ?: "Oops, something went wrong!"
                        )
                    )

                }
            }

            HomeEvent.OnLoadTodos -> viewModelScope.launch {
                try {
                    val todos = todoRepository.all()
                    homeState.postValue(homeState.value?.copy(todos = todos))
                } catch (e: Exception) {
                    uiEffect.postValue(
                        UiEffect.ShowToast(
                            e.message ?: "Oops, something went wrong!"
                        )
                    )
                }
            }

            HomeEvent.OnCreateTodo -> uiEffect.postValue(
                UiEffect.Navigate(HomeFragmentDirections.actionHomeFragmentToCreateOrUpdateFragment())
            )
        }
    }

}