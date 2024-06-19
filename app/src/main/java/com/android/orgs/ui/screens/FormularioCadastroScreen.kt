package com.android.orgs.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.android.orgs.ui.components.ButtonDefault
import com.android.orgs.ui.components.CustomTextField

@Composable
fun FormularioCadastroScreen(navController: NavController?) {
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
            .padding(bottom = 80.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Bottom
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = painterResource(id = R.drawable.orgs_logo_splash),
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 8.dp)
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

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CustomTextField(
                    value = "",
                    onValueChange = { },
                    label = stringResource(id = R.string.text_usuario),
                    placeholder = stringResource(id = R.string.text_usuario)
                )

                CustomTextField(
                    value = "",
                    onValueChange = { },
                    label = stringResource(id = R.string.text_email),
                    placeholder = stringResource(id = R.string.text_email)
                )

                CustomTextField(
                    value = "",
                    onValueChange = { },
                    label = stringResource(id = R.string.text_senha),
                    placeholder = stringResource(id = R.string.text_senha),
                    isPassword = true
                )
            }


            ButtonDefault(text = R.string.text_entrar, onClick = {

            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormularioCadastroScreenPreview() {
    FormularioCadastroScreen(null)
}

