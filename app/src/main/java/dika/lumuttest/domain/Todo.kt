package dika.lumuttest.domain

import dika.lumuttest.api.response.TodoResponse

data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val isCompleted: Boolean
) {
    companion object {
        fun TodoResponse.toTodo(): Todo = Todo(
            userId = userId ?: -1,
            id = id ?: -1,
            title = title.orEmpty(),
            isCompleted = completed ?: false
        )
    }
}