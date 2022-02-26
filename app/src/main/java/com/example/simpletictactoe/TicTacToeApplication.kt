package com.example.simpletictactoe

import android.app.Application
import com.example.simpletictactoe.di.databaseModule
import com.example.simpletictactoe.di.repositoryModule
import com.example.simpletictactoe.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TicTacToeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TicTacToeApplication)
            modules(databaseModule, repositoryModule, viewModelModule)
        }
    }
}