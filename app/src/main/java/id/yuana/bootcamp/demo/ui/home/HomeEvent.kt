package id.yuana.bootcamp.demo.ui.home

sealed class HomeEvent {
    object OnLogoutClick : HomeEvent()
    object OnLoadTodos : HomeEvent()
    object OnCreateTodo : HomeEvent()
}