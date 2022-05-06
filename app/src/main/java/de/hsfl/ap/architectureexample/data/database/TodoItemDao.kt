package de.hsfl.ap.architectureexample.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import de.hsfl.ap.architectureexample.data.model.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoItemDao {
    @Query("SELECT * FROM todo_item")
    fun getAll(): Flow<List<TodoItem>>

    @Query("SELECT * FROM todo_item WHERE uid IN (:itemIds)")
    fun loadAllByIds(itemIds: IntArray): Flow<List<TodoItem>>

    @Insert
    suspend fun insertAll(vararg items: TodoItem)

    @Delete
    fun delete(item: TodoItem)
}