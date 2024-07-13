package com.android.orgs.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

enum class FooterButtons(val icon: ImageVector, val title: String) {
    HOME(Icons.Default.Home, "Início"),
    FAVORITES(Icons.Default.Favorite, "Favoritos"),
    PROFILE(Icons.Default.Person, "Perfil"),
    MENU(Icons.Default.Menu, "Menu")
}

@Composable
fun FooterMenu(navController: NavController?) {

    navController?.let {
        val navBackStackEntry by it.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        var selectedButton by remember { mutableStateOf(FooterButtons.HOME) }

        selectedButton = when (currentRoute) {
            "homeScreen" -> FooterButtons.HOME
            "favoritosScreen" -> FooterButtons.FAVORITES
            "profile" -> FooterButtons.PROFILE
            else -> FooterButtons.MENU
        }

        Column{
            Spacer(modifier = Modifier.weight(1f))
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .background(Color.White),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FooterButtons.values().forEach { button ->
                    FooterMenuItem(
                        icon = button.icon,
                        title = button.title,
                        isActive = button == selectedButton,
                        onClick = {
                            selectedButton = button
                            navController.navigate(
                                when (button){
                                    FooterButtons.HOME -> "homeScreen"
                                    FooterButtons.FAVORITES -> "favoritosScreen"
                                    FooterButtons.PROFILE -> "profile"
                                    FooterButtons.MENU -> "menu"
                                }
                            )
                        }
                    )
                }

            }
        }
    }
}

@Composable
fun FooterMenuItem(icon: ImageVector, title: String, isActive: Boolean, onClick: () -> Unit) {
    Column(
        Modifier
            .padding(horizontal = 24.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isActive) Color(0xFF2A9F85) else Color.Black
        )
        Text(
            text = title,
            fontSize = 12.sp,
            color = if (isActive) Color(0xFF2A9F85) else Color.Black,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FooterMenuPreview() {
    FooterMenu(navController = null)
}