package com.android.orgs.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.orgs.R
import com.android.orgs.extensions.formatarParaMoedaBrasileira
import java.math.BigDecimal

@Composable
fun SugestoesDoDiaBanner(
    nameProduct: String,
    image: Int,
    price: BigDecimal,
    supplier: String
) {
    Row(
        modifier = Modifier
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .height(108.dp)
            .background(Color(0xFFCFDFAC)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically),
            Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = nameProduct,
                fontSize = 16.sp
            )
            Text(
                text = "R$ ${price.formatarParaMoedaBrasileira()}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = supplier,
                fontSize = 12.sp
            )
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .height(92.dp)
                .width(184.dp)
                .align(Alignment.CenterVertically)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f),
                contentScale = ContentScale.FillBounds
            )
        }
        Spacer(modifier = Modifier)

    }
}

@Preview(showBackground = true)
@Composable
private fun SugestoesDoDiaBannerPreview() {
    SugestoesDoDiaBanner(
        nameProduct = "Cesta Grande",
        image = R.drawable.image14,
        price = BigDecimal(70.00),
        supplier = "Pássaro Delivery"
    )
}