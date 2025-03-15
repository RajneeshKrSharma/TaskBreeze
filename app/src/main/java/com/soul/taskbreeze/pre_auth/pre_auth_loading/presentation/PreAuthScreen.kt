package com.soul.taskbreeze.pre_auth.pre_auth_loading.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.decode.GifDecoder
import com.soul.taskbreeze.R
import com.soul.taskbreeze.core.presentation.BaseCompose
import com.soul.taskbreeze.core.presentation.LoadingUi
import com.soul.taskbreeze.core.util.Resource
import com.soul.taskbreeze.pre_auth.presentation.Screen
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreAuthScreen(
    viewModel: PreAuthViewmodel = hiltViewModel(),
    navController: NavController
) {

    BaseCompose(
        topBar = {
            TopAppBar(
                title = { Text(text = "Select to proceed") },
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when(val state = viewModel.preAuthInfoState.value) {
                is Resource.Default -> {}
                is Resource.Error -> Text("Pre auth screen ERROR : ${state.message}")
                is Resource.Loading -> LoadingUi()
                is Resource.Success -> OnSuccessResponseUi(navController = navController)
            }
        }
    }
}

@Composable
fun OnSuccessResponseUi(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .background(color = Color.Transparent)
        ) {
            PageFallAnimatedGrid { selectedScreenOption ->
                when (selectedScreenOption) {
                    PreAuthScreenAvailableScreens.LOCATION -> navController.navigate(
                        Screen.LocationScreen.route
                    )

                    PreAuthScreenAvailableScreens.LOGIN -> navController.navigate(
                        Screen.LoginScreen.route
                    )
                }
            }
        }
    }
}

@Composable
fun PageFallAnimatedGrid(onClick: (PreAuthScreenAvailableScreens) -> Unit) {
    val preAuthAvailableScreens = listOf(
        PreAuthScreenAvailableScreens.LOGIN, PreAuthScreenAvailableScreens.LOCATION
    )

    val gifIcons = listOf(R.drawable.login_icon, R.drawable.location_icon)

    val context = LocalContext.current

    val visibleItems = remember { mutableStateListOf<Boolean>().apply { repeat(preAuthAvailableScreens.size) { add(false) } } }

    LaunchedEffect(Unit) {
        for (index in preAuthAvailableScreens.indices) {
            delay(250)
            visibleItems[index] = true
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(preAuthAvailableScreens) { index, item ->
            AnimatedVisibility(
                visible = visibleItems[index],
                enter = fadeIn(animationSpec = tween(500)) + slideInVertically(
                    initialOffsetY = { it / 2 },
                    animationSpec = tween(500, easing = FastOutSlowInEasing)
                )
            ) {
                // Inside your LazyVerticalGrid
                Card(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onClick(item) },
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.Transparent)
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(Color.Blue, Color.Black)
                                    ),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .clip(RoundedCornerShape(16.dp)),
                        ) {
                            Column(
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Image(
                                    modifier = Modifier.padding(8.dp),
                                    painter = painterResource(gifIcons[index]),
                                    contentDescription = ""
                                )

                                Text(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    text = item.name,
                                    color = Color(0xFFFFD700),
                                    fontSize = 12.sp
                                )

                                Spacer(Modifier.height(10.dp))
                            }
                        }

                        Text(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .offset(x = (10).dp, y = (-20).dp),
                            text = (index + 1).toString(),
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            style = TextStyle(
                                shadow = Shadow(
                                    color = Color.Black,
                                    offset = Offset(7f, 7f),
                                    blurRadius = 10f
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}


enum class PreAuthScreenAvailableScreens {
    LOCATION,
    LOGIN,
}
