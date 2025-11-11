package dika.lumuttest.ui.screens.todoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dika.lumuttest.domain.Todo
import dika.lumuttest.usecase.GetTodosUseCase
import dika.lumuttest.util.stopIfRunning
import io.reactivex.disposables.CompositeDisposable

sealed class TodoListState {
    object Loading: TodoListState()
    data class ShowList(val todos: List<Todo>): TodoListState()
    data class Error(val err: Throwable): TodoListState()
}

class TodoListViewModel(
    private val getTodosUseCase: GetTodosUseCase
): ViewModel() {
    private val _state = MutableLiveData<TodoListState>()
    private val disposable = CompositeDisposable()

    val state: LiveData<TodoListState> = _state


    fun loadData() {
        _state.value = TodoListState.Loading
        getTodosUseCase.execute(
            onSuccess = { data ->
                _state.value = TodoListState.ShowList(data)
            },
            onFailed = { err ->
                _state.value = TodoListState.Error(err)
            }
        )
    }

    fun stop() = disposable.stopIfRunning()
}