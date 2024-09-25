package com.example.dailytodo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao()
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(todo: Todo)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)


    @Query("SELECT * FROM TODO_TABLE ORDER BY id ASC")
     fun getAllTodo():LiveData<List<Todo>>

    @Query("SELECT * FROM TODO_TABLE ORDER BY CASE WHEN priority = 'High' THEN 1 WHEN priority = 'Medium' THEN 2 ELSE 3 END ASC")
     fun getAllHighPriority():LiveData<List<Todo>>

    @Query("SELECT * FROM TODO_TABLE ORDER BY CASE WHEN priority = 'High' THEN 1 WHEN priority = 'Medium' THEN 2 ELSE 3 END DESC")
    fun getAllLowPriority():LiveData<List<Todo>>

}