package com.android.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.orgs.ui.activity.ui.theme.OrgsTheme
import com.android.orgs.ui.screens.FormularioCadastroScreen
import com.android.orgs.ui.screens.FormularioLoginScreen
import com.android.orgs.ui.screens.FornecedorScreen
import com.android.orgs.ui.screens.HomeScreen
import com.android.orgs.ui.screens.TelaInicialDeslogadoScreen
import com.android.orgs.viewmodels.FornecedorViewModel
import com.android.orgs.viewmodels.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrgsTheme {
                val navController = rememberNavController()
                val userViewModel: UserViewModel = viewModel()
                val fornecedorViewModel: FornecedorViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = "telaInicialDeslogadoScreen"
//                    startDestination = "homeScreen"
                ) {
                    composable(
                        route = "telaInicialDeslogadoScreen"
                    ) {
                        TelaInicialDeslogadoScreen(navController)
                    }
                    composable(
                        route = "formularioLoginScreen"
                    ) {
                        FormularioLoginScreen(navController, userViewModel)
                    }
                    composable(
                        route = "formularioCadastroScreen"
                    ) {
                        FormularioCadastroScreen(navController, userViewModel)
                    }
                    composable(
                        route = "homeScreen"
                    ) {
                        HomeScreen(navController, fornecedorViewModel)
                    }
                    composable(
                        route = "fornecedor/{fornecedorId}",
                        arguments = listOf(navArgument("fornecedorId") { type = NavType.LongType })
                    ) { backStackEntry ->
                        val fornecedorId = backStackEntry.arguments?.getLong("fornecedorId")
                        Log.d("Navigation", "Navigating to fornecedor/$fornecedorId")
                        FornecedorScreen(navController = navController, fornecedorId = fornecedorId)
                    }
                }
            }
        }
    }
}