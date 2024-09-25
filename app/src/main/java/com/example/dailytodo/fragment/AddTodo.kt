package com.example.dailytodo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dailytodo.R

import com.example.dailytodo.database.Todo
import com.example.dailytodo.databinding.FragmentAddTodoBinding
import com.example.dailytodo.viewmodel.TodoViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AddTodo :BottomSheetDialogFragment() {
    private var addtodo:FragmentAddTodoBinding?=null
    private val binding get() = addtodo!!

    private val todoViewModel:TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addtodo = FragmentAddTodoBinding.inflate(inflater,container,false)


        binding.apply {

            spinnerPriority.onItemSelectedListener = todoViewModel.listener

          savebtn.setOnClickListener {
              val title = edttexttitle.text.toString().trim()
              val priority = spinnerPriority.selectedItem.toString()
              if (title.isNotEmpty()){
                  val todo = Todo(title = title, priority = todoViewModel.parseStringToPriority(priority) )
                  todoViewModel.insertTodo(todo)
                  findNavController().navigate(R.id.action_addTodo_to_home)
              }else{
                  Toast.makeText(requireContext(),"Please fill required fields",Toast.LENGTH_SHORT).show()
              }


          }

        }


        return binding.root
    }

}