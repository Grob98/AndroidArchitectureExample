package de.hsfl.ap.architectureexample.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_item")
data class TodoItem(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String
){
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}
