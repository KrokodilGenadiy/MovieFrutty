package com.zaus_app.moviefrumy_new.di.modules

import android.content.Context
import com.example.moviefrutty.core.data.api.MovieApi
import com.example.moviefrutty.core.util.PreferenceProvider
import com.example.moviefrutty.home.data.implementations.HomeScreenInteractorImpl
import com.example.moviefrutty.home.data.implementations.HomeScreenRepositoryImpl
import com.example.moviefrutty.home.domain.interactors.HomeScreenInteractor
import com.example.moviefrutty.home.domain.repositories.HomeScreenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext context: Context) = PreferenceProvider(context)

    @Singleton
    @Provides
    fun provideHomeScreenRepository(
        tmdbApi: MovieApi,
        preferenceProvider: PreferenceProvider,
        @ApplicationContext context: Context
    ): HomeScreenRepository = HomeScreenRepositoryImpl(tmdbApi, preferenceProvider, context)

    @Singleton
    @Provides
    fun provideHomeScreenInteractor(repository: HomeScreenRepository): HomeScreenInteractor =
        HomeScreenInteractorImpl(repository)
}