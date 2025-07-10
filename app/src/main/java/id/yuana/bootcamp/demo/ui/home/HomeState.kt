package id.yuana.bootcamp.demo.ui.home

import id.yuana.bootcamp.demo.data.model.Todo

data class HomeState(
    val todos: List<Todo> = emptyList(),
)
