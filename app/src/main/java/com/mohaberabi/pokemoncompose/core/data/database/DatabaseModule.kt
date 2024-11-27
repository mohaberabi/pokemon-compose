package com.mohaberabi.pokemoncompose.core.data.database

import android.content.Context
import androidx.room.Room

import com.mohaberabi.pokemoncompose.core.data.database.dao.PokemonDao
import com.mohaberabi.pokemoncompose.core.data.database.dao.PokemonInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)


object DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context, AppDatabase::class.java,
            "pokemon.db"
        ).build()


    @Provides
    @Singleton
    fun providePokemonDao(db: AppDatabase): PokemonDao = db.pokemonDao()

    @Provides
    @Singleton
    fun providePokemonInfoDao(db: AppDatabase): PokemonInfoDao = db.pokemonInfoDao()


}