package com.android.orgs.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.orgs.ui.activity.ui.theme.OrgsTheme
import com.android.orgs.ui.screens.FormularioCadastroScreen
import com.android.orgs.ui.screens.FormularioLoginScreen
import com.android.orgs.ui.screens.TelaInicialDeslogadoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrgsTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "telaInicialDeslogadoScreen"
                ) {
                    composable(
                        route = "telaInicialDeslogadoScreen"
                    ) {
                        TelaInicialDeslogadoScreen(navController)
                    }
                    composable(
                        route = "formularioLoginScreen"
                    ) {
                        FormularioLoginScreen(navController)
                    }
                    composable(
                        route = "formularioCadastroScreen"
                    ) {
                        FormularioCadastroScreen(navController)
                    }
                }
            }
        }
    }
}