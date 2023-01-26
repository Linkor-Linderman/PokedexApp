package com.example.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedexapp.ui.theme.PokedexAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PokemonListScreen.route
                    ){
                        composable(Screen.PokemonListScreen.route){

                        }
                        composable(
                            Screen.PokemonDetailScreen.route+"/{dominantColor}/{pokemonName}",
                            arguments = listOf(
                                navArgument("dominantColor"){
                                    type = NavType.IntType
                                },
                                navArgument("pokemonName"){
                                    type = NavType.StringType
                                }
                            )
                        ){
                            val dominantColor = remember {
                                val color = it.arguments?.getInt("dominantColor")
                                color?.let { Color(color) } ?: Color.White
                            }
                            val pokemonName = remember {
                                it.arguments?.getString("pokemonName")
                            }
                        }
                    }
                }
            }
        }
    }
}

