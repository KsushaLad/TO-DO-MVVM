package com.example.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.data.ToDoDao
import com.example.todoapp.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData() //получение всех данных
    val sortByHighPriority: LiveData<List<ToDoData>> = toDoDao.sortByHighPriority() //сортировка по высшему приоритету
    val sortByLowPriority: LiveData<List<ToDoData>> = toDoDao.sortByLowPriority() //сортировка по низшему приоритету

    suspend fun insertData(toDoData: ToDoData){ //вставка данных
        toDoDao.insertData(toDoData)
    }

    suspend fun updateData(toDoData: ToDoData){ //обновление данных
        toDoDao.updateData(toDoData)
    }

    suspend fun deleteItem(toDoData: ToDoData){ //удаление Item
        toDoDao.deleteItem(toDoData)
    }

    suspend fun deleteAll(){ //удаление всего
        toDoDao.deleteAll()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>> { //поиск по БД
        return toDoDao.searchDatabase(searchQuery)
    }

}