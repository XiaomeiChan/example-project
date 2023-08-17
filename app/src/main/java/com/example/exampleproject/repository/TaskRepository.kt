package com.example.exampleproject.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.exampleproject.database.Task
import com.example.exampleproject.database.TaskDao
import com.example.exampleproject.utils.FilterUtils
import com.example.exampleproject.utils.TasksFilterType

class TaskRepository(private val tasksDao: TaskDao) {

    companion object {
        const val PAGE_SIZE = 30
        const val PLACEHOLDERS = true

    }

    private fun getFilter(filter: TasksFilterType): SimpleSQLiteQuery =
        FilterUtils.getFilteredQuery(filter)

    fun getTasks(filter: TasksFilterType): LiveData<PagedList<Task>> {
        val filterQuery = getFilter(filter)
        val dataSourceFactory: DataSource.Factory<Int, Task> = tasksDao.getTasks(filterQuery)

        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(PLACEHOLDERS)
            .build()

        return LivePagedListBuilder(dataSourceFactory, config).build()

    }

    fun getTaskById(taskId: Int): LiveData<Task> {
        return tasksDao.getTaskById(taskId)
    }


    suspend fun insertTask(newTask: Task): Long {
        return tasksDao.insertTask(newTask)
    }

    suspend fun deleteTask(task: Task) {
        tasksDao.deleteTask(task)
    }

    suspend fun completeTask(task: Task, isCompleted: Boolean) {
        tasksDao.updateCompleted(task.id, isCompleted)
    }
}