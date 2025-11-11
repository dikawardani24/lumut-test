package dika.lumuttest.usecase.impl

import dika.lumuttest.api.endpoint.LumutEndPoint
import dika.lumuttest.domain.Todo
import dika.lumuttest.domain.Todo.Companion.toTodo
import dika.lumuttest.usecase.GetTodosUseCase
import dika.lumuttest.util.execute
import io.reactivex.disposables.Disposable

class GetTodosUseCaseImpl(
    private val lumutEndPoint: LumutEndPoint
): GetTodosUseCase {

    override fun execute(
        onSuccess: (List<Todo>) -> Unit,
        onFailed: (Throwable) -> Unit
    ): Disposable {
        return lumutEndPoint.getTodos().execute(
            onSuccess = { response ->
                val todos = response.map { it.toTodo() }
                onSuccess(todos)
            },
            onError = { error -> onFailed(error)}
        )
    }
}