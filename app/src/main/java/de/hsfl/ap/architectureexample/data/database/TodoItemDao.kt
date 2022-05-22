package de.hsfl.ap.architectureexample.data.database

import androidx.room.*
import de.hsfl.ap.architectureexample.data.model.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoItemDao {
    @Query("SELECT * FROM todo_item")
    fun getAll(): Flow<List<TodoItem>>

    @Query("SELECT * FROM todo_item WHERE uid IN (:itemIds)")
    fun loadAllByIds(itemIds: IntArray): Flow<List<TodoItem>>

    @Query("SELECT * FROM todo_item WHERE uid = :itemId")
    fun loadById(itemId: Int): Flow<TodoItem>

    @Insert
    suspend fun insertAll(vararg items: TodoItem)

    @Update
    suspend fun update(vararg item: TodoItem)

    @Delete
    suspend fun delete(item: TodoItem)
}