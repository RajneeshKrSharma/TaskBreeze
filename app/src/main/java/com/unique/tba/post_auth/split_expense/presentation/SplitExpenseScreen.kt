package com.unique.tba.post_auth.split_expense.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.unique.tba.post_auth.split_expense.data.remote.dto.GroupExpenseResponseDto
import kotlinx.coroutines.launch
import java.util.Stack

@Composable
fun SplitExpenseScreen(
    navController: NavController,
    splitExpenseViewModel: SplitExpenseViewModel = hiltViewModel()
) {
    val groupExpenseData = splitExpenseViewModel.groupExpenseState.value
    val list = remember { mutableStateListOf<GroupExpenseResponseDto.Data?>() }
    list.clear()
    list.addAll(groupExpenseData.data ?: emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
            .statusBarsPadding() // Ensures padding for the status bar
            .padding(WindowInsets.safeDrawing.asPaddingValues()), // Includes safe area insets
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Group Expenses",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = MaterialTheme.colorScheme.primary
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(list.size) { index ->
                    list[index]?.let { data ->
                        SwipeToDeleteCard(
                            data = data,
                            onDelete = {
                                list.removeAt(index)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SwipeToDeleteCard(
    data: GroupExpenseResponseDto.Data,
    onDelete: () -> Unit
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.Yellow)
            .padding(10.dp)
    ) {
        // Background Card (always visible as the base)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Row {
                    // "Edit" Option (Start)
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(200.dp)
                            .background(Color.Blue.copy(alpha = 0.1f))
                            .clickable {  }

                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .align(Alignment.CenterStart) // Align to start
                                .padding(start = 16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit, // Replace with desired icon
                                contentDescription = "Edit",
                                tint = Color.Blue,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = "Edit",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Blue,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }

                    // "Delete" Option (End)
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(200.dp)
                            .background(Color.Red.copy(alpha = 0.1f))
                            .clickable {  }

                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .align(Alignment.CenterEnd) // Align to end
                                .padding(end = 16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.Red,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = "Delete",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Red,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }


        // Foreground Card (swipeable card)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .offset { IntOffset(offsetX.toInt(), 0) } // Adjust offset for horizontal swiping
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            scope.launch {
                                offsetX = when {
                                    offsetX < -100f -> {
                                        -300f // Swipe out of screen (left)
                                        // Perform left swipe action
                                    }

                                    offsetX > 100f -> {
                                        300f // Swipe out of screen (right)
                                        // Perform right swipe action
                                    }

                                    else -> {
                                        0f // Reset to original position
                                    }
                                }
                            }
                        },
                        onHorizontalDrag = { _, dragAmount ->
                            offsetX += dragAmount
                            // Restrict swipe bounds for smooth behavior
                            offsetX = offsetX.coerceIn(-300f, 300f)
                        }
                    )
                },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = data.grpName ?: "Group Name",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Text(
                    text = "Total Amount: ${data.tAmt ?: "N/A"}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
                Text(
                    text = "Total Items: ${data.tItem ?: "N/A"}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
                Text(
                    text = "Last Settled Amount: ${data.lastSettledAmt ?: "N/A"}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
            }
        }
    }
}
