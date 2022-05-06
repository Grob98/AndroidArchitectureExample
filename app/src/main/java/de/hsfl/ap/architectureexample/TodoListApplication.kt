package de.hsfl.ap.architectureexample

import android.app.Application
import de.hsfl.ap.architectureexample.data.database.AppDatabase
import de.hsfl.ap.architectureexample.data.repositories.TasksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TodoListApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { TasksRepository(database.todoItemDao()) }
}