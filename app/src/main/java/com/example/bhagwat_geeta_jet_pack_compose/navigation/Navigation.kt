package com.example.bhagwat_geeta_jet_pack_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bhagwat_geeta_jet_pack_compose.bottomBar.BottomNavItem
import com.example.bhagwat_geeta_jet_pack_compose.dataModel.BhagwatGeeta
import com.example.bhagwat_geeta_jet_pack_compose.screens.BhagwatGeetaDetailScreen
import com.example.bhagwat_geeta_jet_pack_compose.screens.BhagwatGeetaScreen
import com.example.bhagwat_geeta_jet_pack_compose.screens.GridScreen

@Composable
fun App(navController: NavHostController){
    NavHost(navController = navController, startDestination = BottomNavItem.List.route){
        composable(route =  BottomNavItem.List.route){
            BhagwatGeetaScreen(navController)
        }
        composable(route = "BhagwatGeetaDetailScreen/{flag}",
            arguments = listOf(
                navArgument("flag"){
                    type = NavType.IntType
                }
            )
        ){
            val bhagwatgeeta = navController.previousBackStackEntry?.savedStateHandle?.get<BhagwatGeeta>("bhagwatgeeta")
            val flag =  it.arguments?.getInt("flag") ?: 0
            if (bhagwatgeeta != null) {
                BhagwatGeetaDetailScreen(bhagwatgeeta, navController, flag)
            }
        }
        composable(route = "GridScreen"){
            GridScreen(navController)
        }

        composable(route = BottomNavItem.Grid.route){
            GridScreen(navController)
        }
        }
    }


