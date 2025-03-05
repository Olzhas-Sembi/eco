package com.example.eco.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eco.City
import com.example.eco.CityCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityListScreen(navController: NavController, modifier: Modifier = Modifier) {
    val cities = listOf(
        City("Алматы", 135),
        City("Астана", 60),
        City("Шымкент", 52),
        City("Костанай", 10),
        City("Актобе", 30)
    )

    var searchQuery by remember { mutableStateOf("") }

    val filteredCities = cities.filter {
        it.name.lowercase().contains(searchQuery.lowercase())
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Eco") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF2196F3),
                        titleContentColor = Color.White
                    )
                )
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Поиск городов...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    singleLine = true
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFBBDEFB), Color(0xFF64B5F6))
                    )
                )
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFFE3F2FD), Color(0xFF90CAF9))
                        )
                    )
            ) {
                items(filteredCities) { city ->
                    AnimatedVisibility(visible = true) {
                        CityCard(city, navController)
                    }
                }
            }
        }
    }
}
