package id.yuana.bootcamp.demo.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.yuana.bootcamp.demo.data.repository.AuthRepository
import id.yuana.bootcamp.demo.ui.UiEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    val uiEffect: MutableLiveData<UiEffect> by lazy {
        MutableLiveData<UiEffect>()
    }

    fun onEvent(event: SplashEvent) {
        when (event) {
            SplashEvent.OnGotoLogin -> uiEffect.postValue(UiEffect.Navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment()))
            SplashEvent.OnGotoHome -> uiEffect.postValue(
                UiEffect.Navigate(
                    SplashFragmentDirections.actionSplashFragmentToHomeFragment()
                )
            )

            SplashEvent.OnLoad -> {
                viewModelScope.launch {
                    delay(1000L)
                    if (authRepository.alreadyLogin()) {
                        onEvent(SplashEvent.OnGotoHome)
                    } else {
                        onEvent(SplashEvent.OnGotoLogin)
                    }
                }
            }
        }
    }
}