package com.example.dailytodo.fragment

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.dailytodo.R
import com.example.dailytodo.adapter.TodoAdapter
import com.example.dailytodo.databinding.FragmentHomeBinding
import com.example.dailytodo.viewmodel.TodoViewModel


class Home : Fragment() {
    private var home:FragmentHomeBinding?=null
    private val binding get() = home!!

    private val todoAdapter:TodoAdapter by lazy { TodoAdapter() }

    private val todoViewModel:TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        home = FragmentHomeBinding.inflate(inflater,container,false)

        setRcViewForTodoAdapter()

        todoViewModel.getAllTodo.observe(viewLifecycleOwner){data->
            todoAdapter.setTodo(data)
        }

        setSwipeToDelete()
        setToolbarFeature()

        binding.apply {
            fltactionbtn.setOnClickListener {
                findNavController().navigate(R.id.action_home_to_addTodo)
            }
        }



        return binding.root
    }

    private fun setToolbarFeature() {
        binding.apply {
       toolbar.setOnMenuItemClickListener { item->
           when(item.itemId) {
               R.id.highPriority->{
                   todoViewModel.getAllHighPriority.observe(viewLifecycleOwner){data->
                       todoAdapter.setTodo(data)
                   }
                   true
               }

               R.id.lowPriority->{

                   todoViewModel.getAllLowPriority.observe(viewLifecycleOwner){data->
                       todoAdapter.setTodo(data)
                   }
                   true
               }

               else -> {
                   false
               }
           }
           }
       }
    }

    private fun setSwipeToDelete() {
        val swipeToDelete = object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               val currentItem = viewHolder.adapterPosition
                val todo = todoAdapter.todoList[currentItem]
                todoViewModel.deleteTodo(todo)

            }

        }
        val itemtouchhelper = ItemTouchHelper(swipeToDelete)
        itemtouchhelper.attachToRecyclerView(binding.rcviewtodo)
    }

    private fun setRcViewForTodoAdapter() {
        binding.apply {
            rcviewtodo.adapter = todoAdapter
            rcviewtodo.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        }
    }




}