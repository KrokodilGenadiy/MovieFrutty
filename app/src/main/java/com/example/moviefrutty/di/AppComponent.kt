package com.zaus_app.moviefrumy_new.di

import android.content.Context
import com.example.moviefrutty.home.ui.HomeScreenViewModel
import com.zaus_app.moviefrumy_new.di.modules.DomainModule
import com.zaus_app.moviefrumy_new.di.modules.RemoteModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DomainModule::class,RemoteModule::class])
interface AppComponent {
    fun inject(viewModel: HomeScreenViewModel)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun build(): AppComponent
    }
}
