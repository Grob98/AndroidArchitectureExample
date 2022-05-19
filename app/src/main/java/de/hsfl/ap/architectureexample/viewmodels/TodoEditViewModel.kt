package de.hsfl.ap.architectureexample.viewmodels

import androidx.lifecycle.*
import de.hsfl.ap.architectureexample.data.model.TodoItem
import de.hsfl.ap.architectureexample.data.repositories.TasksRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TodoEditViewModel(private val repository: TasksRepository, editId: Int)
    : ViewModel() {
    var editItem: TodoItem? = null

    val title: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }

    val description: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }

    fun saveTodoItem(title: String, description: String) {
        this.title.value = title
        this.description.value = description

        if (isEditMode()) {
            editTodoItem()
        }else{
            addTodoItem()
        }
    }

    private fun editTodoItem() {
        editItem?.title  = this.title.value!!
        editItem?.description  = this.description.value!!

        viewModelScope.launch {
            editItem?.let { repository.editTodoItem(it) }
        }
    }

    private fun addTodoItem() {
        val title = this.title.value!!
        val description = this.title.value!!

        viewModelScope.launch {
            val todoItem = TodoItem(title, description)
            repository.addTodoItem(todoItem)
        }
    }

    private fun loadTodoItem(id: Int) {
        viewModelScope.launch {
            val item = repository.loadById(id)
            item.collect {
                title.value = it.title
                description.value = it.description
                editItem = it
            }
        }
    }

    private fun isEditMode(): Boolean {
        return editItem != null
    }

    init {
        if (editId > -1) {
            loadTodoItem(editId)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class TodoEditViewModelFactory(private val repository: TasksRepository,
                               private val editId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoEditViewModel::class.java)) {
            return TodoEditViewModel(repository, editId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}