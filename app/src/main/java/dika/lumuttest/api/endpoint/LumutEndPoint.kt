package dika.lumuttest.api.endpoint

import dika.lumuttest.api.response.TodoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface LumutEndPoint {

    @GET("todos")
    fun getTodos(): Single<List<TodoResponse>>

    @GET("todos/{id}")
    fun getTodoDetail(@Path("id") id: Int): Single<TodoResponse>
}