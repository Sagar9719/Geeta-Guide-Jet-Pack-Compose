package com.example.bhagwat_geeta_jet_pack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bhagwat_geeta_jet_pack_compose.bottomBar.BottomNavItem
import com.example.bhagwat_geeta_jet_pack_compose.bottomBar.MainScreen
import com.example.bhagwat_geeta_jet_pack_compose.navigation.App
import com.example.bhagwat_geeta_jet_pack_compose.ui.theme.Bhagwat_Geeta_Jet_Pack_ComposeTheme
import com.example.bhagwat_geeta_jet_pack_compose.viewModel.BhagwatGeetaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MainScreen()
        }
    }
}


