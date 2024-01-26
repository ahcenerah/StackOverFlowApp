package fr.mastersid.stackoverflow.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class QuestionRepositoryModule {
    @Binds
    abstract fun bindQuestionRepository(questionRepositoryImpl: QuestionRepositoryImpl):QuestionRepository
}