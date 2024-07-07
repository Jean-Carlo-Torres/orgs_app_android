package com.android.orgs.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.android.orgs.R
import com.android.orgs.extensions.formatarParaMoedaBrasileira
import com.android.orgs.model.Fornecedor
import com.android.orgs.model.ItemCesta
import com.android.orgs.model.Produto
import com.android.orgs.model.enums.TipoDeVenda
import com.android.orgs.ui.activity.ui.theme.verde
import com.android.orgs.ui.components.ButtonDefault
import com.android.orgs.ui.components.ItemDaCesta
import com.android.orgs.viewmodels.FornecedorViewModel
import com.android.orgs.viewmodels.ProdutoViewModel
import java.math.BigDecimal

@Composable
fun DetalhesProdutoScreen(
    navController: NavController?,
    produtoId: Long?,
    fornecedorId: Long?
) {
    val fornecedorViewModel: FornecedorViewModel = viewModel()
    val produtoViewModel: ProdutoViewModel = viewModel()

    val fornecedor by fornecedorViewModel.fornecedorSelecionado
    val produto by produtoViewModel.produtoSelecionado

    LaunchedEffect(fornecedorId) {
        fornecedorId?.let {
            Log.d("DetalhesProdutoScreen", "Calling getFornecedorById with ID: $it")
            fornecedorViewModel.getFornecedorById(it)
        }
    }

    LaunchedEffect(produtoId) {
        produtoId?.let {
            Log.d("DetalhesProdutoScreen", "Calling getProdutoById with ID: $it")
            produtoViewModel.getProdutoById(it)
        }
    }

    when {
        fornecedor == null || produto == null -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        else -> {
            DetalhesProdutoContent(
                navController = navController,
                produto = produto!!,
                fornecedor = fornecedor!!
            )
        }
    }
}

@Composable
fun DetalhesProdutoContent(
    navController: NavController?,
    produto: Produto,
    fornecedor: Fornecedor
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            AsyncImage(
                model = produto.imagem,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(238.dp)
                    .background(Color.Green),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopStart)
                    .background(Color.White, CircleShape)
                    .size(32.dp)
                    .clickable { navController?.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = produto.nome,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = fornecedor.image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = fornecedor.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Text(
                text = produto.descricao,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = "${produto.valor.formatarParaMoedaBrasileira()}",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = verde
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                ButtonDefault(text = R.string.text_comprar_cesta) {}
            }

            Text(
                text = "Itens da cesta",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            val itensDaCesta = listOf(
                ItemCesta(
                    nome = "Bananas",
                    imagem = "https://www.daysoftheyear.com/wp-content/uploads/banana-day1-scaled.jpg",
                    tipoDeVenda = TipoDeVenda.POR_KG,
                    quantidade = 2
                ),
                ItemCesta(
                    nome ="Maçãs",
                    imagem = "https://parade.com/.image/t_share/MTkwNTgxNDY1MzcxMTkxMTY0/different-types-of-apples-jpg.jpg",
                    tipoDeVenda = TipoDeVenda.AVULSA,
                    quantidade = 8
                ),
                ItemCesta(
                    nome ="Melancias",
                    imagem = "https://www.mundoecologia.com.br/wp-content/uploads/2019/05/Melancia-6-2.jpg",
                    tipoDeVenda = TipoDeVenda.AVULSA,
                    quantidade = 1
                ),
                ItemCesta(
                    nome ="Morangos",
                    imagem = "https://www.ceasa-ce.com.br/wp-content/uploads/sites/87/2019/09/morango.jpg",
                    tipoDeVenda = TipoDeVenda.POR_KG,
                    quantidade = 1
                ),
                ItemCesta(
                    nome ="Uvas",
                    imagem = "https://sandevid.com/wp-content/uploads/2020/04/grapes-4617986_1920-1536x1024-1.jpg",
                    tipoDeVenda = TipoDeVenda.POR_KG,
                    quantidade = 1
                )
            )

            itensDaCesta.forEach { item ->
                ItemDaCesta(item = item)
            }
        }
    }
}

val exampleProduto = Produto(
    id = 0,
    nome = "Cesta extra grande",
    descricao = "Reprehenderit posuere rerum aliquip possimus aptent alias quos sint nisl morbi autem.",
    valor = BigDecimal(70.0),
    imagem = "https://via.placeholder.com/64",
    usuarioId = null
)

val exampleFornecedor = Fornecedor(
    id = 1L,
    image = "https://images.squarespace-cdn.com/content/5e90f51f5542933b842d0395/1587586237132-7NOEIO0H744OKSCH9A0P/logo.png?content-type=image%2Fpng",
    title = "Jenny Jack",
    rating = BigDecimal(5),
    distance = BigDecimal(2.1)
)

@Composable
fun DetalhesProdutoScreenPreview() {
    val produto = exampleProduto
    val fornecedor = exampleFornecedor

    DetalhesProdutoContent(
        navController = null,
        produto = produto,
        fornecedor = fornecedor
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewDetalhesProdutoScreen() {
    DetalhesProdutoScreenPreview()
}
