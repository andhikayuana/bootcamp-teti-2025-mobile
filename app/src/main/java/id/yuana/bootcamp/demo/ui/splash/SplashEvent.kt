package id.yuana.bootcamp.demo.ui.splash

sealed class SplashEvent {
    object OnLoad : SplashEvent()
    object OnGotoLogin : SplashEvent()
    object OnGotoHome : SplashEvent()
}