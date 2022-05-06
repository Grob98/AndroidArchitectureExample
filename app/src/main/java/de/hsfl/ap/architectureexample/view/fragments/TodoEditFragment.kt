package de.hsfl.ap.architectureexample.view.fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import de.hsfl.ap.architectureexample.R
import de.hsfl.ap.architectureexample.TodoListApplication
import de.hsfl.ap.architectureexample.databinding.TodoEditFragmentBinding
import de.hsfl.ap.architectureexample.databinding.TodoListFragmentBinding
import de.hsfl.ap.architectureexample.view.TodoItemListAdapter
import de.hsfl.ap.architectureexample.viewmodels.TodoEditViewModel
import de.hsfl.ap.architectureexample.viewmodels.TodoEditViewModelFactory
import de.hsfl.ap.architectureexample.viewmodels.TodoListViewModel
import de.hsfl.ap.architectureexample.viewmodels.TodoListViewModelFactory

class TodoEditFragment : Fragment() {

    companion object {
        fun newInstance() = TodoEditFragment()
    }

    private var _binding: TodoEditFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TodoEditViewModel by viewModels {
        TodoEditViewModelFactory((activity?.application as TodoListApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TodoEditFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        //Title
        val titleObserver = Observer<String> {
            binding.etTitle.setText(it)
        }
        viewModel.title.observe(viewLifecycleOwner, titleObserver)

        //Description
        val descriptionObserver = Observer<String> {
            binding.etDescription.setText(it)
        }
        viewModel.description.observe(viewLifecycleOwner, descriptionObserver)

        //Cancel
        binding.bCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        //Save
        binding.bSave.setOnClickListener {
            viewModel.addTodoItem(binding.etTitle.text.toString(),
                binding.etDescription.text.toString())
        }

        return view
    }
}