package com.mohaberabi.pokemoncompose.core.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mohaberabi.pokemoncompose.core.data.database.entity.PokemonInfoEntity


@Dao
interface PokemonInfoDao {
    @Upsert
    suspend fun insertPokemonInfo(info: PokemonInfoEntity)

    @Query("SELECT * FROM pokemoninfoentity WHERE name=:name")
    suspend fun getPokemonInfo(name: String): PokemonInfoEntity?
}