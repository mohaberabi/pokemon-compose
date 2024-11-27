package com.mohaberabi.pokemoncompose.core.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mohaberabi.pokemoncompose.core.data.database.entity.PokemonEntity


@Dao
interface PokemonDao {


    @Upsert

    suspend fun insertPokemon(pokemon: List<PokemonEntity>)


    @Query("SELECT * FROM pokemonentity WHERE page =:page")
    suspend fun getPokemonList(page: Int): List<PokemonEntity>

}