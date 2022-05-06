package de.hsfl.ap.architectureexample.viewmodels

import androidx.lifecycle.*
import de.hsfl.ap.architectureexample.data.model.TodoItem
import de.hsfl.ap.architectureexample.data.repositories.TasksRepository
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch

class TodoEditViewModel(private val repository: TasksRepository) : ViewModel() {
    val title: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }

    val description: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }

    fun addTodoItem(title: String, description: String) {
        this.title.value = title
        this.description.value = description

        val todoItem = TodoItem(this.title.value!!, this.description.value!!)
        viewModelScope.launch {
            repository.addTodoItem(todoItem)

            repository.allTodoItems.collect {
                it.forEach {
                    println(it.title)
                }
            }
        }
    }

    init {

    }
}

@Suppress("UNCHECKED_CAST")
class TodoEditViewModelFactory(private val repository: TasksRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoEditViewModel::class.java)) {
            return TodoEditViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}