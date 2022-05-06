package de.hsfl.ap.architectureexample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import de.hsfl.ap.architectureexample.data.repositories.TasksRepository

class TodoListViewModel(repository: TasksRepository) : ViewModel() {

    val todoItems = repository.allTodoItems.asLiveData()

    init {

    }
}

@Suppress("UNCHECKED_CAST")
class TodoListViewModelFactory(private val repository: TasksRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoListViewModel::class.java)) {
            return TodoListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}