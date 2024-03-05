package com.example.bhagwat_geeta_jet_pack_compose.popUpBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.bhagwat_geeta_jet_pack_compose.R
import kotlinx.coroutines.delay

@Composable
fun PopUpBar() {
    val Color1 = colorResource(id = R.color.light_purple)
    var isPopupVisible by remember { mutableStateOf(true) }

    LaunchedEffect(isPopupVisible){
        if(isPopupVisible){
            delay(1000)
            isPopupVisible = false
        }
    }

    if (isPopupVisible) {

        Popup(onDismissRequest = { isPopupVisible = false }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(Color1)
                    .fillMaxWidth()
            ) {
                Card(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                        .graphicsLayer(clip = false),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color1)
                    ) {
                        Text(
                            text ="Click On Card To Open Chapter",
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 20.sp,
                        )
                    }
                }
            }
        }
    }
}



