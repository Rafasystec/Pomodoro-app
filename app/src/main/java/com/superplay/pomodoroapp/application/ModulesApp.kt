package com.superplay.pomodoroapp.application

import androidx.room.Room
import com.superplay.pomodoroapp.dao.PomodoroDao
import com.superplay.pomodoroapp.database.PomodoroDB
import com.superplay.pomodoroapp.datasource.PomodoroDS
import com.superplay.pomodoroapp.repository.PomodoroRespository
import com.superplay.pomodoroapp.ui.main.HistoryPomodoroViewModel
import com.superplay.pomodoroapp.ui.main.NewPomodoroViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

    /*
    --------------------------------------------------------------------
    Precisamos desse arquivo para o Koin conseguir injetar as depedências
    de forma correta.
    --------------------------------------------------------------------
     */

    val repositories = module {
        fun providePomodoroRepository(pomodoroDao: PomodoroDao) : PomodoroRespository{
            return PomodoroDS(pomodoroDao)
        }
        single { providePomodoroRepository(get())}
    }

    val dataSources = module {
//        factory { PomodoroDS(get()) }
    }

    val viewModels = module {
        viewModel { NewPomodoroViewModel(get()      )}
        viewModel { HistoryPomodoroViewModel(get()  )}
    }

    //A comment here
    val databaseModule = module {
        fun provideDataBase(application: Application):PomodoroDB{
            return Room.databaseBuilder(
                    application,
                    PomodoroDB::class.java,
                    "app_pomodoro"
            ).build()
        }
        fun providePomodoroDao(database : PomodoroDB):PomodoroDao{
            return database.pomodoroDAO()
        }

        single { provideDataBase(Application.getInstance())}
        single {providePomodoroDao(get())}
    }
