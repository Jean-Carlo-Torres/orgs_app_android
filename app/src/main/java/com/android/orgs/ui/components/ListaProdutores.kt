package com.android.orgs.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.android.orgs.model.Fornecedor
import com.android.orgs.ui.activity.ui.theme.background
import com.android.orgs.ui.activity.ui.theme.verde
import com.android.orgs.viewmodels.FornecedorViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal

@Composable
fun ListaProdutores(navController: NavController?, fornecedorViewModel: FornecedorViewModel?) {
    var coroutineScope = rememberCoroutineScope()

    val fornecedores = listOf(
        Fornecedor(
            id = 1L,
            image = "https://images.squarespace-cdn.com/content/5e90f51f5542933b842d0395/1587586237132-7NOEIO0H744OKSCH9A0P/logo.png?content-type=image%2Fpng",
            title = "Jenny Jack",
            rating = BigDecimal(5),
            distance = BigDecimal(2.1)
        ),
        Fornecedor(
            id = 2L,
            image = "https://masterbundles.com/wp-content/uploads/2023/03/leaf-ai-599.jpg",
            title = "Folhas Delivery",
            rating = BigDecimal(5),
            distance = BigDecimal(2.1)
        ),
        Fornecedor(
            id = 3L,
            image = "https://img.freepik.com/premium-vector/organic-vegetables-badge-icon-vector-farm-veggie-pumpkin-red-bell-pepper-artichoke-isolated-organic-farm-market-grocery-vegetables-icon_8071-5519.jpg",
            title = "Vote Green",
            rating = BigDecimal(5),
            distance = BigDecimal(2.1)
        ),
        Fornecedor(
            id = 4L,
            image = "https://img.freepik.com/premium-vector/vector-logo-illustration-potato-mascot-cartoon-style_116762-8665.jpg",
            title = "Potager",
            rating = BigDecimal(5),
            distance = BigDecimal(2.1)
        )
    )

    Column(
        Modifier.fillMaxSize()
    ) {
        Text(
            text = "Mais produtores",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                top = 32.dp,
                bottom = 8.dp,
            )
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            fornecedores.forEach { fornecedor ->
                LaunchedEffect(fornecedor) {
                    coroutineScope.launch {
                        fornecedorViewModel?.cadastrar(fornecedor)
                    }
                }
                ListaProdutoresItem(fornecedor, onClick = {
                    fornecedor.id?.let {
                        navController?.navigate("fornecedor/$it")
                    }
                })
            }
        }
    }
}

@Composable
fun ListaProdutoresItem(
    fornecedor: Fornecedor,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .height(80.dp)
            .background(background)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .size(66.dp)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.CenterVertically)
        ) {
            AsyncImage(
                model = fornecedor.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f),
                contentScale = ContentScale.FillBounds
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = fornecedor.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                RatingStars(rating = fornecedor.rating)
            }
            Text(
                text = String.format("%.2f Km", fornecedor.distance),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun RatingStars(rating: BigDecimal) {
    Row {
        val ratingInt = rating.toInt()
        for (i in 1..5) {
            val starColor = if (i <= ratingInt) verde else Color.Gray
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = starColor,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListaProdutoresPreview() {
    ListaProdutores(navController = null, null)
}
