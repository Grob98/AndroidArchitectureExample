package de.hsfl.ap.architectureexample.data.repositories

import de.hsfl.ap.architectureexample.data.database.TodoItemDao
import de.hsfl.ap.architectureexample.data.model.TodoItem
import kotlinx.coroutines.flow.Flow

class TasksRepository(private val todoItemDao: TodoItemDao) {
    val allTodoItems: Flow<List<TodoItem>> = todoItemDao.getAll()

    suspend fun addTodoItem(todoItem: TodoItem) {
        todoItemDao.insertAll(todoItem)
    }
}