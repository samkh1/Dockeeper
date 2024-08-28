package com.sri.dockeeper.di

import com.sri.dockeeper.data.DocumentDao
import com.sri.dockeeper.data.mapper.DocumentMapper
import com.sri.dockeeper.data.repository.DocumentDaoRepository_Imp
import com.sri.dockeeper.domain.repository.DocumentDaoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMapper(): DocumentMapper {
        return DocumentMapper()
    }
    @Singleton
    @Provides
    fun provideRepository(dao: DocumentDao, mapper: DocumentMapper): DocumentDaoRepository {
        return DocumentDaoRepository_Imp(dao, mapper)
    }
}