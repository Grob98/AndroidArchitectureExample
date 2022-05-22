package de.hsfl.ap.architectureexample.data.repositories

import de.hsfl.ap.architectureexample.data.database.TodoItemDao
import de.hsfl.ap.architectureexample.data.model.TodoItem
import kotlinx.coroutines.flow.Flow

class TasksRepository(private val todoItemDao: TodoItemDao) {
    val allTodoItems: Flow<List<TodoItem>> = todoItemDao.getAll()

    fun loadById(id: Int): Flow<TodoItem> {
        return todoItemDao.loadById(id)
    }

    suspend fun addTodoItem(todoItem: TodoItem) {
        todoItemDao.insertAll(todoItem)
    }

    suspend fun editTodoItem(todoItem: TodoItem) {
        println("update $todoItem")
        todoItemDao.update(todoItem)
    }

    suspend fun deleteTodoItem(todoItem: TodoItem) {
        todoItemDao.delete(todoItem)
    }
}