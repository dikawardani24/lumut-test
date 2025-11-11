package dika.lumuttest.usecase

import dika.lumuttest.domain.Todo
import io.reactivex.disposables.Disposable

interface GetTodosUseCase {
    fun execute(
        onSuccess: (List<Todo>) -> Unit,
        onFailed: (Throwable) -> Unit
    ): Disposable
}

