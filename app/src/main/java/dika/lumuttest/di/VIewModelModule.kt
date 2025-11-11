package dika.lumuttest.di

import dika.lumuttest.ui.screens.todoDetail.TodoDetailViewModel
import dika.lumuttest.ui.screens.todoList.TodoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object VIewModelModule {

    fun get() = module {
        viewModelOf(::TodoListViewModel)
        viewModelOf(::TodoDetailViewModel)
    }
}