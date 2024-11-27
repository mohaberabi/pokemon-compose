package com.mohaberabi.pokemoncompose.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

import com.mohaberabi.pokemoncompose.core.data.database.dao.PokemonDao
import com.mohaberabi.pokemoncompose.core.data.database.dao.PokemonInfoDao
import com.mohaberabi.pokemoncompose.core.data.database.entity.PokemonEntity
import com.mohaberabi.pokemoncompose.core.data.database.entity.PokemonInfoEntity


@Database(
    entities = [PokemonInfoEntity::class, PokemonEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    abstract fun pokemonInfoDao(): PokemonInfoDao

}