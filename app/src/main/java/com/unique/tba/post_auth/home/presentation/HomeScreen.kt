package com.unique.tba.post_auth.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.unique.tba.pre_auth.presentation.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewmodel = hiltViewModel()
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Home Screen")
            Button(onClick = {
                viewModel.logout()
                navController.navigate(Screen.LoginScreen.route)

            }) {
                Text(text = "Logout")
            }
            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                navController.navigate(Screen.SplitExpenseScreen.route)
            }) {
                Text(text = "Split Expense")

            }
        }
    }
}