package dika.lumuttest.ui.screens.todoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dika.lumuttest.R
import dika.lumuttest.databinding.FragmentTodoListBinding
import dika.lumuttest.domain.Todo
import dika.lumuttest.ui.adapter.ItemTodoAdapter
import dika.lumuttest.ui.screens.todoDetail.TodoDetailFragmentArgs
import dika.lumuttest.util.hideLoading
import dika.lumuttest.util.showLoading
import dika.lumuttest.util.showMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class TodoListFragment : Fragment(), ItemTodoAdapter.OnTodoSelected {
    private lateinit var binding: FragmentTodoListBinding
    private val itemAdapter = ItemTodoAdapter()
    private val viewModel: TodoListViewModel by viewModel()

    private fun showData(todos: List<Todo>) {
        itemAdapter.items = todos
        itemAdapter.notifyItemInserted(0)
    }

    private fun initView() = with(binding) {
        with(rv) {
            itemAdapter.listener = this@TodoListFragment
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeVm() = viewModel.state.observe(this) { state ->
        if (state is TodoListState.Loading) {
            requireContext().showLoading()
            return@observe
        }
        hideLoading()
        if (state is TodoListState.ShowList) {
            showData(state.todos)
            return@observe
        }
        if (state is TodoListState.Error) {
            showMessage(state.err.localizedMessage ?: "Unknown Error Occurred")
            return@observe
        }
    }

    override fun onSelected(todo: Todo) {
        val direction = TodoListFragmentDirections.actionTodoListFragmentToTodoDetailFragment(
            id = todo.id
        )
        findNavController().navigate(direction)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeVm()
        viewModel.loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.stop()
    }
}