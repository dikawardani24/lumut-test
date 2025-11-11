package dika.lumuttest.usecase

import dika.lumuttest.domain.Todo
import io.reactivex.disposables.Disposable

interface GetTodoDetailUseCase {
    fun execute(
        id: Int,
        onSuccess: (Todo) -> Unit,
        onFailed: (Throwable) -> Unit
    ): Disposable
}