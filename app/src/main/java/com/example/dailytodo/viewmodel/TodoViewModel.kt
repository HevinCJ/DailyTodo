package com.example.dailytodo.viewmodel

import android.app.Application
import android.graphics.Color
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailytodo.R
import com.example.dailytodo.database.Todo
import com.example.dailytodo.database.TodoDao
import com.example.dailytodo.database.TodoDatabase
import com.example.dailytodo.helper.Priority
import kotlinx.coroutines.launch

class TodoViewModel(application: Application):AndroidViewModel(application) {

    private val todoDao:TodoDao
     val getAllTodo:LiveData<List<Todo>>
     val getAllHighPriority:LiveData<List<Todo>>
    val getAllLowPriority:LiveData<List<Todo>>

    init {
        todoDao = TodoDatabase.getDatabase(application).todoDao()
        getAllTodo = todoDao.getAllTodo()
        getAllHighPriority = todoDao.getAllHighPriority()
        getAllLowPriority = todoDao.getAllLowPriority()
    }


    fun parseStringToPriority(priority: String):Priority{
        return when(priority){
            "High"-> Priority.High
            "Medium"-> Priority.Medium
            "Low"-> Priority.Low
            else -> {
                Priority.High
            }
        }
    }

    fun insertTodo(todo: Todo){
        viewModelScope.launch {
            todoDao.insertTodo(todo)
        }
    }

    fun updateTodo(todo: Todo){
        viewModelScope.launch {
            todoDao.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo){
        viewModelScope.launch {
            todoDao.deleteTodo(todo)
        }
    }

    val listener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0->(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,R.color.red))
                1->(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,R.color.yellow))
                2->(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,R.color.green))
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

    }

}