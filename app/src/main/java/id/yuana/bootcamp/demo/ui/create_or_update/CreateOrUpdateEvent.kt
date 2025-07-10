package id.yuana.bootcamp.demo.ui.create_or_update

sealed class CreateOrUpdateEvent {
    data class OnTitleChange(val title: String) : CreateOrUpdateEvent()
    data class OnDescriptionChange(val description: String) : CreateOrUpdateEvent()
    object OnCreateOrUpdateClick : CreateOrUpdateEvent()
}