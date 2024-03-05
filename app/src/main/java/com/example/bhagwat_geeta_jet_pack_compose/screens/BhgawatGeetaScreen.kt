package com.example.bhagwat_geeta_jet_pack_compose.screens

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
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
import com.example.bhagwat_geeta_jet_pack_compose.bottomBar.BottomNavItem
import com.example.bhagwat_geeta_jet_pack_compose.dataModel.BhagwatGeeta
import com.example.bhagwat_geeta_jet_pack_compose.viewModel.BhagwatGeetaViewModel
import kotlinx.coroutines.delay

@Composable
fun BhagwatGeetaScreen(navController: NavHostController) {
    val viewModel: BhagwatGeetaViewModel = hiltViewModel()
    val context = LocalContext.current
    val bhagwatGeetas by viewModel.bhagwatGeetas.observeAsState(emptyList())

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
                    .background(Color.LightGray)
            ) {
                items(bhagwatGeetas) { bhagwatGeetaItem ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp, 12.dp, 12.dp, 12.dp)
                            .background(Color.Gray),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        shape = RoundedCornerShape(4.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(2.dp, 6.dp, 2.dp, 2.dp),
                            horizontalArrangement = Arrangement.Absolute.Center
                        ) {
                            Text(
                                text = "Chapter - ",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Text(
                                text = bhagwatGeetaItem.chapter_number.toString()
                                    ?: "Name Transliterated Not Availiable",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Text(text = " : ")
                            Text(
                                text = bhagwatGeetaItem.name_transliterated
                                    ?: "Name Transliterated Not Availiable",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(2.dp, 6.dp, 2.dp, 6.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {
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
                                },
                            ) {
                                Text(text = "Summary")
                            }
                        }
                    }
                }
            }
        }
    }
}






