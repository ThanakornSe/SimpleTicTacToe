package com.example.simpletictactoe.di

import com.example.simpletictactoe.database.HistoryDB
import com.example.simpletictactoe.repository.HistoryRepository
import com.example.simpletictactoe.repository.HistoryRepositoryImpl
import com.example.simpletictactoe.viewmodel.HistoryViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single { HistoryDB.getDatabase(androidContext()).historyDao }
}

val repositoryModule = module {
    single<HistoryRepository> { HistoryRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { HistoryViewModel(get()) }
}