package dika.lumuttest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dika.lumuttest.databinding.ItemTodoBinding
import dika.lumuttest.domain.Todo
import dika.lumuttest.util.setAsStatus

class ItemTodoAdapter: RecyclerView.Adapter<ItemTodoAdapter.ViewHolder>() {
    var items: List<Todo> = emptyList()
    var listener: OnTodoSelected? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val todo = items[position]
        holder.bind(
            todo = todo,
            listener = listener
        )
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(
        private val binding: ItemTodoBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: Todo, listener: OnTodoSelected? = null) = with(binding) {
            title.text = todo.title
            status.setAsStatus(todo.isCompleted, false)
            statusCv.setAsStatus(todo.isCompleted)

            if (listener != null) {
                root.setOnClickListener { listener.onSelected(todo) }
            }
        }
    }

    interface OnTodoSelected {
        fun onSelected(todo: Todo)
    }
}