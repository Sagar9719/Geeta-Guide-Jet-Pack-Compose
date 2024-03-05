package com.example.bhagwat_geeta_jet_pack_compose.bottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bhagwat_geeta_jet_pack_compose.navigation.App
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import javax.annotation.meta.When


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
         },
        content = {
            Row(
                     verticalAlignment = Alignment.CenterVertically,
                     horizontalArrangement = Arrangement.SpaceEvenly,
                     modifier = Modifier.padding(it),
                 ) {
                     App(navController = navController)
                 }
        }
    )
}

@Composable
fun BottomBar(navController: NavHostController){
    val screens = listOf(
        BottomNavItem.List,
        BottomNavItem.Grid
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBardestination = screens.any {it.route == currentDestination?.route}
    if(bottomBardestination) {
        BottomNavigation(
            modifier = Modifier.background(Color.Gray),
            backgroundColor = Color.LightGray
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen:  BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    if (currentDestination != null) {
        BottomNavigationItem(
            icon = {
                Icon(painter = painterResource(id = screen.icon), contentDescription = "Bottom Bar")
            },
            selected = currentDestination.hierarchy?.any {
                it.route == screen.route
            } == true,
            unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
            onClick = {
             navController.navigate(screen.route){
                 popUpTo(navController.graph.findStartDestination().id)
                 launchSingleTop = true
             }
            },
        )
    }
}


