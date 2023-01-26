package com.example.pokedexapp.di

import com.example.pokedexapp.common.Constants.BASE_URL
import com.example.pokedexapp.data.remote.PokemonApi
import com.example.pokedexapp.data.repository.PokemonRepositoryImpl
import com.example.pokedexapp.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokemonRepository(): PokemonApi {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PokemonApi::class.java)
    }

    @Singleton
    @Provides
    fun providePokemonApi(api: PokemonApi) : PokemonRepository{
        return PokemonRepositoryImpl(api)
    }
}