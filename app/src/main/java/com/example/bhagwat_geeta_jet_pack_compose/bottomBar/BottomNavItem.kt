package com.example.bhagwat_geeta_jet_pack_compose.bottomBar
import com.example.bhagwat_geeta_jet_pack_compose.R

sealed class BottomNavItem(
    val route: String,
    val icon: Int
) {
    object Home :
            BottomNavItem(
                "Home View",
                R.drawable.home
            )

    object Grid :
            BottomNavItem(
                "Grid View",
                R.drawable.grid
            )

    object List:
            BottomNavItem(
                "List View",
                R.drawable.list1
            )
}