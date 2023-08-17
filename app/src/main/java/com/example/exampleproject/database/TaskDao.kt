package com.example.exampleproject.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface TaskDao {

    @RawQuery(observedEntities = [Task::class])
    fun getTasks(query: SupportSQLiteQuery): DataSource.Factory<Int, Task>

    @Query("Select * from tasks where id = :taskId")
    fun getTaskById(taskId: Int): LiveData<Task>

    @Insert
    suspend fun insertTask(task: Task): Long

    @Insert
    fun insertAll(vararg tasks: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("Update tasks SET isCompleted = :completed where id = :taskId")
    suspend fun updateCompleted(taskId: Int, completed: Boolean)

}
