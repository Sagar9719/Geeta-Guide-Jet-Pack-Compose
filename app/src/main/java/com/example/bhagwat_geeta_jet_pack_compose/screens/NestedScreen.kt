package com.example.bhagwat_geeta_jet_pack_compose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.bhagwat_geeta_jet_pack_compose.dataModel.BhagwatGeeta
import com.example.bhagwat_geeta_jet_pack_compose.dataModel.BhagwatGeetaChild
import com.example.bhagwat_geeta_jet_pack_compose.dataModel.imageList
import com.example.bhagwat_geeta_jet_pack_compose.viewModel.BhagwatGeetaViewModel
import kotlinx.coroutines.delay


@Composable
fun NestedScreen(navController: NavHostController) {
    val viewModel: BhagwatGeetaViewModel = hiltViewModel()
    val context = LocalContext.current
    val bhagwatGeetas by viewModel.bhagwatGeetas.observeAsState(emptyList())
    var rotationState by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchBhagwatGeeta()
        while (true) {
            delay(16) // Adjust delay for smoother animation
            rotationState += 4f // Adjust rotation speed
        }
    }
    ConstraintLayout {
        if (bhagwatGeetas.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    Icons.Rounded.Refresh, contentDescription = "Localized description",
                    modifier = Modifier.graphicsLayer(rotationZ = rotationState)
                )
            }
        }
        else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 8.dp, 0.dp, 0.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Bhagwat Geeta",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Red
                )
            }
            Column(
                modifier = Modifier
                    .padding(0.dp, 50.dp, 0.dp, 0.dp)
                    .background(Color.White)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(4.dp, 4.dp, 4.dp, 4.dp)
                        .background(Color.LightGray)
                ) {
                    items(bhagwatGeetas) {
                        ColumnItemUI(it, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun  ColumnItemUI(bhagwatGeeta: BhagwatGeeta, navController: NavHostController) {
    val bhagwatGeetaChildList = imageList.map { imageid ->
        BhagwatGeetaChild(imageid)
    }
    Card(
        modifier = Modifier
            .padding(4.dp, 4.dp, 4.dp, 4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = {
            val bhagwatgeeta = BhagwatGeeta(
                id = bhagwatGeeta.id,
                name = bhagwatGeeta.name,
                chapter_summary = bhagwatGeeta.chapter_summary,
                chapter_summary_hindi = bhagwatGeeta.chapter_summary_hindi,
                name_meaning = bhagwatGeeta.name_meaning,
                name_transliterated = bhagwatGeeta.name_transliterated,
                verses_count = bhagwatGeeta.verses_count,
                slug = bhagwatGeeta.slug,
                name_translated = bhagwatGeeta.name_translated,
                chapter_number = bhagwatGeeta.chapter_number
            )
            navController.currentBackStackEntry?.savedStateHandle?.set(
                key = "bhagwatgeeta",
                value = bhagwatgeeta
            )
            navController.navigate("BhagwatGeetaDetailScreen/${0}")
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp, 3.dp, 3.dp, 3.dp),
            horizontalArrangement = Arrangement.Center
        ) {
           Text(
               text = bhagwatGeeta.name_transliterated,
               fontWeight = FontWeight.Bold,
               fontSize = 16.sp
           )
        }
       LazyRow(
           modifier = Modifier
               .padding(4.dp, 4.dp, 4.dp, 4.dp)
               .background(Color.LightGray)
       ){
           items(bhagwatGeetaChildList){
               RowItemUI(it)
           }
       }
    }
}

@Composable
fun RowItemUI(bhagwatGeetaChild: BhagwatGeetaChild) {
    Box(
        modifier = Modifier.padding(2.dp, 2.dp, 2.dp, 2.dp)
               .height(190.dp),
    ){
        Image(
            painter = painterResource(id = bhagwatGeetaChild.image),
            contentDescription = "BhagwatGeetaImage"
        )
    }
}