package com.example.eco
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.eco.data.repository.CityRepositoryImpl
import com.example.eco.di.appModule
import com.example.eco.domain.repository.CityRepository
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import com.example.eco.navigation.AppNavigation
import com.example.eco.ui.theme.EcoTheme
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Инициализация Koin
        startKoin {
            androidContext(this@MainActivity)
            modules(appModule) // Добавь сюда свой модуль с зависимостями
        }
        setContent {
            EcoTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}

// Модуль с зависимостями
val appModule = module {
    single<CityRepository> { CityRepositoryImpl(get()) }  // Пример зависимости
}
