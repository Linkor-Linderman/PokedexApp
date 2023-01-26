package com.example.pokedexapp.data.repository

import com.example.pokedexapp.common.Resource
import com.example.pokedexapp.data.remote.PokemonApi
import com.example.pokedexapp.data.remote.dto.Pokemon
import com.example.pokedexapp.data.remote.dto.PokemonList
import com.example.pokedexapp.domain.repository.PokemonRepository
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ActivityScoped
class PokemonRepositoryImpl @Inject constructor(
   private val api: PokemonApi
): PokemonRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        }catch (e: HttpException){
            return Resource.Error<PokemonList>(e.message() ?: "An unknown error occured")
        }catch (e: IOException){
            return Resource.Error<PokemonList>("Connection error")
        }
        return Resource.Success<PokemonList>(response)
    }

    override suspend fun getPokemonDetail(name: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonDetail(name)
        }catch (e: HttpException){
            return Resource.Error<Pokemon>(e.message() ?: "An unknown error occured")
        }catch (e: IOException){
            return Resource.Error<Pokemon>("Connection error")
        }
        return Resource.Success<Pokemon>(response)
    }

}