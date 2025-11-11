package dika.lumuttest.ui.screens.todoDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dika.lumuttest.domain.Todo
import dika.lumuttest.usecase.GetTodoDetailUseCase
import dika.lumuttest.util.stopIfRunning
import io.reactivex.disposables.CompositeDisposable

sealed class TodoDetailState {
    object Loading: TodoDetailState()
    data class ShowDetail(val todo: Todo): TodoDetailState()
    data class Error(val err: Throwable): TodoDetailState()
}

class TodoDetailViewModel(
    private val getTodoDetailUseCase: GetTodoDetailUseCase
): ViewModel() {
    private val _state = MutableLiveData<TodoDetailState>()
    private val disposable = CompositeDisposable()

    val state: LiveData<TodoDetailState> = _state

    fun loadDetail(id: Int) {
        if (id <= 0) {
            _state.value = TodoDetailState.Error(RuntimeException("Invalid id"))
            return
        }
        _state.value = TodoDetailState.Loading
        getTodoDetailUseCase.execute(
            id = id,
            onSuccess = { data ->
                _state.value = TodoDetailState.ShowDetail(data)
            },
            onFailed = { err ->
                _state.value = TodoDetailState.Error(err)
            }
        )
    }

    fun stop() = disposable.stopIfRunning()
}