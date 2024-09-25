package com.example.dailytodo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dailytodo.R
import com.example.dailytodo.database.Todo
import com.example.dailytodo.databinding.FragmentUpdateTodoBinding
import com.example.dailytodo.viewmodel.TodoViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UpdateTodo : BottomSheetDialogFragment() {
    private var updatefrag:FragmentUpdateTodoBinding?=null
    private val  binding get() = updatefrag!!

    private val updateTodoArgs by navArgs<UpdateTodoArgs>()

    private val todoViewModel:TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        updatefrag = FragmentUpdateTodoBinding.inflate(inflater,container,false)

        setDataInFields()

        updateTodo()

        binding.apply {
            spinnerPriority.onItemSelectedListener = todoViewModel.listener
        }




        return binding.root
    }

    private fun updateTodo() {
        binding.apply {
            savebtn.setOnClickListener {
                val id = updateTodoArgs.updatetodo.id
                val title = edttexttitle.text.toString().trim()
                val priority = todoViewModel.parseStringToPriority(spinnerPriority.selectedItem.toString())
                if (title.isNotEmpty()){
                    val updateTodo = Todo(id,title,priority)
                    todoViewModel.updateTodo(updateTodo)
                    findNavController().navigate(R.id.action_updateTodo_to_home)
                    Toast.makeText(requireContext(),"Field:$title Updated",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(),"Please fill required fields", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun setDataInFields() {
        binding.apply {
            edttexttitle.setText(updateTodoArgs.updatetodo.title)
            spinnerPriority.setSelection(updateTodoArgs.updatetodo.priority.ordinal)
        }
    }


}