package com.android.orgs.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.android.orgs.R
import com.android.orgs.ui.activity.ui.theme.background
import com.android.orgs.ui.components.BannerTop
import com.android.orgs.ui.components.SearchTextField

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.background(color = background)
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
                    .padding(horizontal = 16.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.orgs_logo_splash),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .width(100.dp)
                        .height(38.dp)
                )

                Column(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 16.dp)
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
                        text = stringResource(R.string.text_preencha_as_informacoes),
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                    )

                    SearchTextField(searchText = "Pesquisar") {}
                }
                BannerTop(
                    icone = R.drawable.ic_delivery,
                    title = "Entrega Rápida",
                    subtitle = "Veja as melhores da semana",
                    image = R.drawable.image14,
                    onClick = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenScreen() {
    HomeScreen()
}