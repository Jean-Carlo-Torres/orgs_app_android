package com.android.orgs.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.orgs.R
import com.android.orgs.ui.activity.ui.theme.verde

@Composable
fun BannerTop(
    icone: Int? = null,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    image: Int
) {
    Row(
        modifier = Modifier
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .height(146.dp)
            .background(Color(0xFFFFEEFF))
            .clip(RoundedCornerShape(16.dp)),
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (icone != null) {
                Icon(
                    painter = painterResource(id = icone),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
            )
            Button(
                onClick = onClick,
                modifier = Modifier
                    .widthIn(min = 52.dp)
                    .height(31.dp),
                colors = ButtonDefaults.buttonColors(verde)
            ) {
                Text(text = stringResource(id = R.string.text_ver))
            }
        }
        Box(
            modifier = Modifier.height(146.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFFFEEFF)),
                alignment = Alignment.CenterEnd
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BannerTopPreview() {
    BannerTop(
        icone = R.drawable.ic_delivery,
        title = "Entrega Rápida",
        subtitle = "Veja as melhores da semana",
        image = R.drawable.image14,
        onClick = {}
    )
}