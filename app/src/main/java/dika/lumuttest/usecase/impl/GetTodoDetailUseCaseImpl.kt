package dika.lumuttest.usecase.impl

import dika.lumuttest.api.endpoint.LumutEndPoint
import dika.lumuttest.domain.Todo
import dika.lumuttest.domain.Todo.Companion.toTodo
import dika.lumuttest.usecase.GetTodoDetailUseCase
import dika.lumuttest.util.execute
import io.reactivex.disposables.Disposable

class GetTodoDetailUseCaseImpl(
    private val lumutEndPoint: LumutEndPoint
): GetTodoDetailUseCase {
    override fun execute(
        id: Int,
        onSuccess: (Todo) -> Unit,
        onFailed: (Throwable) -> Unit
    ): Disposable {
        return lumutEndPoint.getTodoDetail(id = id).execute(
            onSuccess = { response ->
                val todo = response.toTodo()
                onSuccess(todo)
            },
            onError = {error -> onFailed(error) }
        )
    }
}