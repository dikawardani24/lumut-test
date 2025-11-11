package dika.lumuttest.di

import dika.lumuttest.usecase.GetTodoDetailUseCase
import dika.lumuttest.usecase.GetTodosUseCase
import dika.lumuttest.usecase.impl.GetTodoDetailUseCaseImpl
import dika.lumuttest.usecase.impl.GetTodosUseCaseImpl
import org.koin.dsl.module

object UseCaseModule {
    fun get() = module {
        factory<GetTodoDetailUseCase> { GetTodoDetailUseCaseImpl(get()) }
        factory<GetTodosUseCase> { GetTodosUseCaseImpl(get()) }
    }
}