package com.example.bhagwat_geeta_jet_pack_compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.example.bhagwat_geeta_jet_pack_compose.dataModel.BhagwatGeeta
import com.example.bhagwat_geeta_jet_pack_compose.viewModel.BhagwatGeetaViewModel

@Composable
fun GridScreen(navController: NavHostController) {
    val viewModel: BhagwatGeetaViewModel = hiltViewModel()
    val context = LocalContext.current
    val bhagwatGeetas: State<List<BhagwatGeeta>> =
        viewModel.bhagwatGeetas.observeAsState(emptyList())
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchBhagwatGeeta()
    }

    ConstraintLayout {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 8.dp, 0.dp, 0.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bhagwat Geeta Grid View",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Red
            )
        }
        Column(
            modifier = Modifier.padding(0.dp, 50.dp, 0.dp, 0.dp)
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.SpaceAround,
                content = {
                    items(bhagwatGeetas.value) { bhagwatGeetaItem ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .background(Color.LightGray)
                                .padding(2.dp, 2.dp, 2.dp, 2.dp),
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(6.dp, 6.dp, 6.dp, 6.dp)
                            ) {
                                Text(
                                    text = "Chapter - ${bhagwatGeetaItem.chapter_number}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = bhagwatGeetaItem.name_transliterated,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(6.dp, 6.dp, 6.dp, 6.dp)
                            ) {
                                Button(onClick = {
                                    val bhagwatgeeta = BhagwatGeeta(
                                        id = bhagwatGeetaItem.id,
                                        name = bhagwatGeetaItem.name,
                                        chapter_summary = bhagwatGeetaItem.chapter_summary,
                                        chapter_summary_hindi = bhagwatGeetaItem.chapter_summary_hindi,
                                        name_meaning = bhagwatGeetaItem.name_meaning,
                                        name_transliterated = bhagwatGeetaItem.name_transliterated,
                                        verses_count = bhagwatGeetaItem.verses_count,
                                        slug = bhagwatGeetaItem.slug,
                                        name_translated = bhagwatGeetaItem.name_translated,
                                        chapter_number = bhagwatGeetaItem.chapter_number
                                    )
                                    navController.currentBackStackEntry?.savedStateHandle?.set(
                                        key = "bhagwatgeeta",
                                        value = bhagwatgeeta
                                    )
                                    navController.navigate("BhagwatGeetaDetailScreen/${1}")
                                }) {
                                    Text(text = "Summary")
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}