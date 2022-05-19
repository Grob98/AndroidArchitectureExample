package de.hsfl.ap.architectureexample.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.hsfl.ap.architectureexample.R
import de.hsfl.ap.architectureexample.data.model.TodoItem

class TodoItemListAdapter(private val todoClickListener: TodoClickListener) :
    ListAdapter<TodoItem, TodoItemListAdapter.TodoItemHolder>(TodoItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemHolder {
        return TodoItemHolder.create(parent, todoClickListener)
    }

    override fun onBindViewHolder(holder: TodoItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class TodoItemHolder(private val view: View, private val todoClickListener: TodoClickListener)
        : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val tvTitle: TextView = view.findViewById(R.id.tvItemTitle)
        private val tvDescription: TextView = view.findViewById(R.id.tvItemDescription)

        fun bind(item: TodoItem) {
            tvTitle.text = item.title
            tvDescription.text = item.description
            view.setOnClickListener(this)
        }

        companion object {
            fun create(parent: ViewGroup, todoClickListener: TodoClickListener): TodoItemHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.todo_list_item, parent, false)
                return TodoItemHolder(view, todoClickListener)
            }
        }

        override fun onClick(p0: View?) {
            this.todoClickListener.onTodoClick(adapterPosition)
        }
    }

    class TodoItemComparator : DiffUtil.ItemCallback<TodoItem>() {
        override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem.title == newItem.title && oldItem.description == newItem.description
        }
    }

    interface TodoClickListener {
        fun onTodoClick(position: Int)
    }
}