package dika.lumuttest.ui.screens.todoDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dika.lumuttest.databinding.FragmentTodoDetailBinding
import dika.lumuttest.domain.Todo
import dika.lumuttest.util.hideLoading
import dika.lumuttest.util.setAsStatus
import dika.lumuttest.util.showLoading
import dika.lumuttest.util.showMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class TodoDetailFragment : Fragment() {
    private lateinit var binding: FragmentTodoDetailBinding
    private val viewModel: TodoDetailViewModel by viewModel()

    private fun showData(todo: Todo) = with(binding) {
        id.text = todo.id.toString()
        title.text = todo.title
        status.setAsStatus(todo.isCompleted)
    }

    private fun observeState() = viewModel.state.observe(this) { state ->
        if (state is TodoDetailState.Loading) {
            requireContext().showLoading()
            return@observe
        }
        hideLoading()
        if (state is TodoDetailState.ShowDetail) {
            showData(state.todo)
            return@observe
        }
        if (state is TodoDetailState.Error) {
            showMessage(state.err.localizedMessage ?: "Unknown Error Occurred")
            return@observe
        }
    }

    private fun loadDetail() {
        val args = TodoDetailFragmentArgs.fromBundle(requireArguments())
        viewModel.loadDetail(args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
        loadDetail()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.stop()
    }
}