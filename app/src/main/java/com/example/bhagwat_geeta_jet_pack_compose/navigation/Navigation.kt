package com.example.bhagwat_geeta_jet_pack_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bhagwat_geeta_jet_pack_compose.bottomBar.BottomNavItem
import com.example.bhagwat_geeta_jet_pack_compose.dataModel.BhagwatGeeta
import com.example.bhagwat_geeta_jet_pack_compose.screens.BhagwatGeetaDetailScreen
import com.example.bhagwat_geeta_jet_pack_compose.screens.BhagwatGeetaScreen
import com.example.bhagwat_geeta_jet_pack_compose.screens.GridScreen
import com.example.bhagwat_geeta_jet_pack_compose.screens.NestedScreen

@Composable
fun App(navController: NavHostController){
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route){
        composable(route =  BottomNavItem.Home.route){
            NestedScreen(navController)
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

        composable(route = BottomNavItem.List.route){
            BhagwatGeetaScreen(navController)
        }
        }
    }


