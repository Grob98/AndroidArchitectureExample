package de.hsfl.ap.architectureexample.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import de.hsfl.ap.architectureexample.R
import de.hsfl.ap.architectureexample.TodoListApplication
import de.hsfl.ap.architectureexample.databinding.TodoListFragmentBinding
import de.hsfl.ap.architectureexample.data.model.TodoItem
import de.hsfl.ap.architectureexample.view.TodoItemListAdapter
import de.hsfl.ap.architectureexample.viewmodels.TodoListViewModel
import de.hsfl.ap.architectureexample.viewmodels.TodoListViewModelFactory

class TodoListFragment : Fragment() {

    companion object {
        fun newInstance() = TodoListFragment()
    }

    private var _binding: TodoListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TodoListViewModel by viewModels {
        TodoListViewModelFactory((activity?.application as TodoListApplication).repository)
    }

    private lateinit var adapter: TodoItemListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TodoListFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        adapter = TodoItemListAdapter()

        //RecyclerView
        binding.rvTodoList.layoutManager = LinearLayoutManager(context)
        binding.rvTodoList.adapter = adapter
        viewModel.todoItems.observe(viewLifecycleOwner, todoItemsObserver)

        binding.fabAddTodo.setOnClickListener {
            val action = TodoListFragmentDirections.actionTodoListFragmentToTodoEditFragment()
            findNavController().navigate(action)
        }

        return view
    }

    private val todoItemsObserver = Observer<List<TodoItem>> { list ->
        list.forEach {
            println(it)
        }
        adapter.submitList(list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}