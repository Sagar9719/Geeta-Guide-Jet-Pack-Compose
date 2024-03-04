package com.example.bhagwat_geeta_jet_pack_compose.bottomBar

import com.example.bhagwat_geeta_jet_pack_compose.R

sealed class BottomNavItem(
    var title: String,
    val icon: Int
) {
    object List :
            BottomNavItem(
                "List View",
                R.drawable.list1
            )

    object Grid :
            BottomNavItem(
                "Grid View",
                R.drawable.grid
            )
}