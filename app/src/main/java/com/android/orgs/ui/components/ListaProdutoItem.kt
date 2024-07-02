package com.android.orgs.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.android.orgs.extensions.formatarParaMoedaBrasileira
import com.android.orgs.model.Produto
import com.android.orgs.ui.activity.ui.theme.verde
import java.math.BigDecimal

@Composable
fun ListaProdutoItem(produto: Produto) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White)
            .padding(8.dp)
    ) {
        AsyncImage(
            model = produto.imagem,
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        ) {
            Text(
                text = produto.nome,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = produto.descricao,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = produto.valor.formatarParaMoedaBrasileira(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = verde
            )
        }
    }
}

@Preview
@Composable
private fun ListaProdutoItemPreview() {
    ListaProdutoItem(
        Produto(
            id = 0,
            nome = "Cesta extra grande",
            descricao = "Reprehenderit posuere rerum aliquip possimus aptent alias quos sint nisl morbi autem.",
            valor = BigDecimal(70.0),
            imagem = "https://via.placeholder.com/64",
            usuarioId = null
        ),
    )
}