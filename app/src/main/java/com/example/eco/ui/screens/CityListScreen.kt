package com.example.eco.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eco.City
import com.example.eco.CityCard
import com.example.eco.domain.repository.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityListScreen(navController: NavController, modifier: Modifier = Modifier) {
    val cityRepository: CityRepository = get()
    val cities = listOf(
        "Алматы", "Астана", "Шымкент", "Костанай", "Актобе",
        "Караганда", "Павлодар", "Усть-Каменогорск", "Тараз", "Семей"
    )

    var cityData by remember { mutableStateOf<List<City>>(emptyList()) }
    var searchQuery by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                val fetched = cityRepository.fetchCityData(cities)
                cityData = fetched
            }
        }
    }

    val filteredCities = cityData.filter {
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
                    CityCard(city, navController)
                }
            }
        }
    }
}
