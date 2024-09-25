package com.example.dailytodo.adapter

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.dailytodo.R
import com.example.dailytodo.database.Todo
import com.example.dailytodo.fragment.HomeDirections
import com.example.dailytodo.helper.Priority

class BindingAdapter {

    companion object{

        @BindingAdapter("android:ParsePriorityIntoColor")
        @JvmStatic
        fun ParsePriorityIntoColor(view:ImageView,priority: Priority){
            when(priority){
                Priority.High -> view.setColorFilter(view.context.getColor(R.color.red))
                Priority.Medium -> view.setColorFilter(view.context.getColor(R.color.orange))
                Priority.Low -> view.setColorFilter(view.context.getColor(R.color.green))
            }
        }

        @BindingAdapter("android:navigateToUpdateTodo")
        @JvmStatic
        fun navigateToUpdateTodo(view:ConstraintLayout,todo:Todo){
            view.setOnClickListener {
                val action = HomeDirections.actionHomeToUpdateTodo(todo)
                view.findNavController().navigate(action)
            }

        }

    }

}