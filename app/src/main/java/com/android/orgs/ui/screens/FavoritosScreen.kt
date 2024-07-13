package com.android.orgs.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.orgs.R
import com.android.orgs.ui.activity.ui.theme.background
import com.android.orgs.ui.components.FooterMenu
import com.android.orgs.ui.components.ListaProdutoresItem
import com.android.orgs.viewmodels.FornecedorViewModel
import com.android.orgs.viewmodels.UserViewModel
import kotlinx.coroutines.launch

@SuppressLint("RememberReturnType")
@Composable
fun FavoritosScreen(
    navController: NavController?,
    userViewModel: UserViewModel?,
    fornecedorViewModel: FornecedorViewModel?
) {

    val fornecedoresFavoritosIds = remember { userViewModel?.getFornecedoresFavoritosIds() }
    val fornecedoresFavoritos by remember {
        mutableStateOf(fornecedoresFavoritosIds?.let {
            fornecedorViewModel?.getFornecedoresFavoritos(it)
        })
    }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background)
            .padding(bottom = 64.dp)
    ) {
        Box {
            Box(
                Modifier.align(Alignment.TopEnd)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "Favoritos",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 32.dp, start = 16.dp)
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    coroutineScope.launch {
                        fornecedoresFavoritos?.let {
                            items(fornecedoresFavoritos ?: emptyList()) { fornecedor ->
                                ListaProdutoresItem(
                                    fornecedor = fornecedor,
                                    onClick = {
                                        navController?.navigate("detalhesFornecedor/${fornecedor.id}")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    FooterMenu(navController = navController)
}

@Preview(showBackground = true)
@Composable
private fun FavoritosScreenPreview() {
    FavoritosScreen(navController = null, null, null)
}