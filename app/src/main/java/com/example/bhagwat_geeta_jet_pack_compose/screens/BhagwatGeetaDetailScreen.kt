package com.example.bhagwat_geeta_jet_pack_compose.screens

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.bhagwat_geeta_jet_pack_compose.dataModel.BhagwatGeeta
import kotlinx.coroutines.launch


@Composable
fun BhagwatGeetaDetailScreen(items: BhagwatGeeta, navController: NavHostController, flag: Int) {
    ConstraintLayout(
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 8.dp, 0.dp, 0.dp),
                    horizontalArrangement = Arrangement.Absolute.SpaceAround
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "Chapter - ${items.id}",
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = items.name_transliterated,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                }
            Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceAround
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "Verses_Count - ${items.verses_count}",
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = items.name_meaning,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                }
            Divider(color = Color.Black, thickness = 1.dp)
            }
        }
            HorizontalPagerScreen(items)

            if (flag == 0) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(0.dp, 500.dp, 0.dp, 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                        navController.navigate("GridScreen") {
                            navController.popBackStack()
                        }
                    }) {
                        Text(
                            text = "Grid View",
                            modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 8.dp),
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }



    @Composable
    @OptIn(ExperimentalFoundationApi::class)
    fun HorizontalPagerScreen(items: BhagwatGeeta) {
        val pagerState = rememberPagerState { 2 }
        val pages = (1..2).map {
            when (it) {
                1 -> {
                    items.name_transliterated
                }

                else -> {
                    items.name
                }
            }
        }
        val scrollCoroutineScope = rememberCoroutineScope()
        ConstraintLayout{
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(0.dp, 76.dp, 0.dp, 0.dp)
            ) {
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    indicator = {
                        TabRowDefaults.Indicator(
                            Modifier.tabIndicatorOffset(it[pagerState.currentPage])
                        )
                    },
                ) {
                    pages.forEachIndexed { index, title ->
                        Text(
                            text = title,
                            modifier = Modifier
                                .clickable {
                                    scrollCoroutineScope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                }
                                .padding(8.dp)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 250.dp)
                    .height(200.dp)
            ) {
                HorizontalPager(
                    pageSize = object : PageSize {
                        override fun Density.calculateMainAxisPageSize(
                            availableSpace: Int,
                            pageSpacing: Int
                        ): Int {
                            return ((availableSpace - 2 * pageSpacing) * 1f).toInt()
                        }
                    },
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth()
                ) { page ->
                    when (page) {
                        0 -> {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(8.dp, 8.dp, 8.dp, 8.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp, 4.dp, 4.dp, 4.dp),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = items.name_transliterated,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp, 4.dp, 4.dp, 4.dp),
                                ) {
                                    Text(
                                        text = items.chapter_summary,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Light,
                                        fontFamily = FontFamily.SansSerif,
                                        textAlign = TextAlign.Justify
                                    )
                                }
                            }
                        }

                        1 -> {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(8.dp, 8.dp, 8.dp, 8.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp, 4.dp, 4.dp, 4.dp),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = items.name,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp, 4.dp, 4.dp, 4.dp),
                                ) {
                                    Text(
                                        text = items.chapter_summary_hindi,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Light,
                                        fontFamily = FontFamily.SansSerif,
                                        textAlign = TextAlign.Justify
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }













