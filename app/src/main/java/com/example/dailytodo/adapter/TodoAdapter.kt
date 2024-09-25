package com.example.dailytodo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.dailytodo.database.Todo
import com.example.dailytodo.databinding.TodoadapterLayoutBinding

class TodoAdapter:RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

     var todoList:List<Todo> = emptyList()

    class TodoViewHolder(private val binding:TodoadapterLayoutBinding):ViewHolder(binding.root){

        fun bindTodo(todo: Todo){
            binding.todoData =todo
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(TodoadapterLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

        val currentTodo = todoList[position]
        holder.bindTodo(currentTodo)

    }


    fun setTodo(todo: List<Todo>){
        this.todoList = todo
        notifyDataSetChanged()
    }
}