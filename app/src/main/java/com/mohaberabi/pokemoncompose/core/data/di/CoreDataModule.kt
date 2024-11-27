package com.mohaberabi.pokemoncompose.core.data.di

import com.mohaberabi.pokemoncompose.core.data.database.dao.PokemonDao
import com.mohaberabi.pokemoncompose.core.data.database.dao.PokemonInfoDao
import com.mohaberabi.pokemoncompose.core.data.network.HttpClientFactory
import com.mohaberabi.pokemoncompose.core.data.repository.OfflineFirstPokemonRepository
import com.mohaberabi.pokemoncompose.core.data.source.local.PokemonRoomLocalDataSource
import com.mohaberabi.pokemoncompose.core.data.source.remote.KtorPokemonRemoteDataSource
import com.mohaberabi.pokemoncompose.core.domain.model.DefaultDispatchersProvider
import com.mohaberabi.pokemoncompose.core.domain.model.DispatchersProvider
import com.mohaberabi.pokemoncompose.core.domain.repository.PokemonRepository
import com.mohaberabi.pokemoncompose.core.domain.source.PokemonLocalDataSource
import com.mohaberabi.pokemoncompose.core.domain.source.PokemonRemoteDataSource
import com.mohaberabi.pokemoncompose.core.domain.usecase.GetPokemonInfoUseCase
import com.mohaberabi.pokemoncompose.core.domain.usecase.GetPokemonsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoreDataModule {
    @Singleton
    @Provides
    fun provideDispathers(): DispatchersProvider = DefaultDispatchersProvider()


    @Singleton
    @Provides
    fun provideGetInfoUseCase(
        repo: PokemonRepository,
    ) = GetPokemonInfoUseCase(repo)


    @Singleton
    @Provides
    fun provideGetPokemonUseCase(
        repo: PokemonRepository,
    ) = GetPokemonsUseCase(repo)

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: PokemonRemoteDataSource,
        localSource: PokemonLocalDataSource
    ): PokemonRepository = OfflineFirstPokemonRepository(remoteDataSource, localSource)

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        dispatchers: DispatchersProvider,
        client: HttpClient
    ): PokemonRemoteDataSource = KtorPokemonRemoteDataSource(dispatchers, client)

    @Singleton
    @Provides
    fun provideLocalSource(
        dispatchers: DispatchersProvider,
        infoDao: PokemonInfoDao,
        pokemonDao: PokemonDao,
    ): PokemonLocalDataSource = PokemonRoomLocalDataSource(
        dispatchers = dispatchers,
        pokemonInfoDao = infoDao,
        pokemonDao = pokemonDao
    )

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClientFactory().create()
}