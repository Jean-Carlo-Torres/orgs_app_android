package com.android.orgs.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.orgs.R
import com.android.orgs.ui.activity.ui.theme.background
import com.android.orgs.ui.components.BannerTop
import com.android.orgs.ui.components.FooterMenu
import com.android.orgs.ui.components.ListaProdutores
import com.android.orgs.ui.components.SearchTextField
import com.android.orgs.ui.components.SugestoesDoDiaBanner
import java.math.BigDecimal

@Composable
fun HomeScreen(navController: NavController?) {
    Column(
        modifier = Modifier
            .background(color = background)
            .verticalScroll(rememberScrollState())
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
                Image(
                    painter = painterResource(id = R.drawable.orgs_logo_splash),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 32.dp, start = 16.dp)
                        .width(100.dp)
                        .height(38.dp)
                )

                Column(
                    modifier = Modifier
                        .padding(
                            top = 20.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {
                    Text(
                        text = stringResource(id = R.string.text_bem_vindo),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    )

                    Text(
                        text = stringResource(R.string.text_explore_pelos_melhores_produtos),
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                    )

                    SearchTextField(searchText = "Pesquisar") {}

                    Spacer(modifier = Modifier.height(20.dp))
                    BannerTop(
                        icone = R.drawable.ic_delivery,
                        title = "Entrega Rápida",
                        subtitle = "Veja as melhores da semana",
                        image = R.drawable.image14,
                        onClick = {}
                    )
                }

                Text(
                    text = "Cestas populares",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        top = 32.dp,
                        bottom = 8.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    SugestoesDoDiaBanner(
                        nameProduct = "Cesta Grande",
                        image = R.drawable.image14,
                        price = BigDecimal(70.00),
                        supplier = "Pássaro Delivery"
                    )
                    SugestoesDoDiaBanner(
                        nameProduct = "Cesta Grande",
                        image = R.drawable.image14,
                        price = BigDecimal(70.00),
                        supplier = "Pássaro Delivery"
                    )
                    SugestoesDoDiaBanner(
                        nameProduct = "Cesta Grande",
                        image = R.drawable.image14,
                        price = BigDecimal(70.00),
                        supplier = "Pássaro Delivery"
                    )
                }
                Column(
                    Modifier.padding(horizontal = 16.dp)
                ) {
                    ListaProdutores(navController = navController)
                }
            }
        }
    }
    FooterMenu(navController = navController)
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenScreen() {
    HomeScreen(null)
}