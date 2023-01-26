package com.example.pokedexapp.domain.repository

import com.example.pokedexapp.common.Resource
import com.example.pokedexapp.data.remote.dto.Pokemon
import com.example.pokedexapp.data.remote.dto.PokemonList

interface PokemonRepository {

    suspend fun getPokemonList( limit: Int, offset: Int ): Resource<PokemonList>

    suspend fun getPokemonDetail( name: String ): Resource<Pokemon>

}