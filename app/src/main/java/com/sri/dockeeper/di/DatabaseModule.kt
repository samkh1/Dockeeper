package com.sri.dockeeper.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sri.dockeeper.data.DocumentDao
import com.sri.dockeeper.data.entities.DocumentEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [DocumentEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun docDao(): DocumentDao
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context = context,
            AppDataBase::class.java,
            "documents",
        ).build()
    }

    @Provides
    fun provideDocDao(dataBase: AppDataBase): DocumentDao {
        return dataBase.docDao()
    }
}
