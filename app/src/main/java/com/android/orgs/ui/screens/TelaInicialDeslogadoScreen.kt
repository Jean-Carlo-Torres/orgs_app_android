package com.android.orgs.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.android.orgs.ui.activity.ui.theme.verde
import com.android.orgs.ui.components.ButtonDefault

@Composable
fun TelaInicialDeslogadoScreen(navController: NavController?) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFDEE6EC)),
            alignment = Alignment.TopCenter
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 150.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Bottom
    )
    {
        Column(
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = painterResource(id = R.drawable.orgs_logo_splash),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 8.dp)
                    .width(100.dp)
                    .height(38.dp)
            )

            Text(
                text = stringResource(id = R.string.text_bem_vindo),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = stringResource(id = R.string.text_explore_pelos_melhores_produtos),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Box(
                Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            ) {
                ButtonDefault(R.string.text_entrar, onClick = {
                    navController?.navigate("formularioLoginScreen")
                })
            }

            Button(
                onClick = {
                    navController?.navigate("formularioCadastroScreen")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .border(2.dp, color = verde, shape = CircleShape)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(
                    text = stringResource(id = R.string.text_cadastrar_usuario),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = verde
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TelaInicialDeslogadoScreenPreview() {
    TelaInicialDeslogadoScreen(null)
}
