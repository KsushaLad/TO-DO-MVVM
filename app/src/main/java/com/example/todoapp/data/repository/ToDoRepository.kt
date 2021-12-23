package com.example.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.data.ToDoDao
import com.example.todoapp.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData() //получение всех данных
    val sortByHighPriority: LiveData<List<ToDoData>> = toDoDao.sortByHighPriority() //сортировка по высшей приоритетности
    val sortByLowPriority: LiveData<List<ToDoData>> = toDoDao.sortByLowPriority() //сортировка по низкой приоритетности

    suspend fun insertData(toDoData: ToDoData){ //встака данных
        toDoDao.insertData(toDoData)
    }

    suspend fun updateData(toDoData: ToDoData){ //обновление данных
        toDoDao.updateData(toDoData)
    }

    suspend fun deleteItem(toDoData: ToDoData){ //удаление Item
        toDoDao.deleteItem(toDoData)
    }

    suspend fun deleteAll(){ //удаление всех
        toDoDao.deleteAll()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>> { //поиск базы данных
        return toDoDao.searchDatabase(searchQuery)
    }

}