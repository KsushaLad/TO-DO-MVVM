package com.example.todoapp.fragments.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.todoapp.data.models.ToDoData

class ToDoDiffUtil(private val oldList: List<ToDoData>, private val newList: List<ToDoData>): DiffUtil.Callback() {

    override fun getOldListSize(): Int { //получение списка старого размера
        return oldList.size
    }

    override fun getNewListSize(): Int { //получение списка нового размера
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean { //являются ли Item  одинаковыми
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean { //является ли одинаковым содержимое
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].title == newList[newItemPosition].title
                && oldList[oldItemPosition].description == newList[newItemPosition].description
                && oldList[oldItemPosition].priority == newList[newItemPosition].priority
    }
}