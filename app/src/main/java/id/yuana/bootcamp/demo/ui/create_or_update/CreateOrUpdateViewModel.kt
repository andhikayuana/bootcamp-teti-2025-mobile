package id.yuana.bootcamp.demo.ui.create_or_update

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.yuana.bootcamp.demo.data.model.Todo
import id.yuana.bootcamp.demo.data.repository.TodoRepository
import id.yuana.bootcamp.demo.ui.UiEffect
import kotlinx.coroutines.launch

class CreateOrUpdateViewModel(
    private val todoRepository: TodoRepository
) : ViewModel() {

    val todoState: MutableLiveData<Todo> by lazy {
        MutableLiveData<Todo>(Todo())
    }
    val uiEffect: MutableLiveData<UiEffect> by lazy {
        MutableLiveData<UiEffect>()
    }

    fun onEvent(event: CreateOrUpdateEvent) {
        when (event) {
            CreateOrUpdateEvent.OnCreateOrUpdateClick -> viewModelScope.launch {
                try {
                    todoState.value?.let {
                        todoRepository.createOrUpdate(it)
                    }
                    uiEffect.postValue(UiEffect.PopBackStack)
                } catch (e: Exception) {
                    uiEffect.postValue(
                        UiEffect.ShowToast(
                            e.message ?: "Oops, something went wrong!"
                        )
                    )
                }
            }

            is CreateOrUpdateEvent.OnDescriptionChange -> todoState.postValue(
                todoState.value?.copy(
                    description = event.description
                )
            )

            is CreateOrUpdateEvent.OnTitleChange -> todoState.postValue(todoState.value?.copy(title = event.title))
        }
    }
}