package com.ruavee.mentalcountingapp.di

import com.ruavee.mentalcountingapp.data.repository.ProblemRepository
import com.ruavee.mentalcountingapp.domain.usecase.GenerateProblemUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideProblemRepository(): ProblemRepository = ProblemRepository()

    @Provides
    @Singleton
    fun provideGenerateProblemUseCase(
        repository: ProblemRepository
    ): GenerateProblemUseCase = GenerateProblemUseCase(repository)
}