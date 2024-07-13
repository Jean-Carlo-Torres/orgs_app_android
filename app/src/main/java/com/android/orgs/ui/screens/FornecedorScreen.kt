package com.android.orgs.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.android.orgs.model.Fornecedor
import com.android.orgs.model.Produto
import com.android.orgs.ui.components.ListaProdutoItem
import com.android.orgs.viewmodels.FornecedorViewModel
import com.android.orgs.viewmodels.ProdutoViewModel
import com.android.orgs.viewmodels.UserViewModel
import kotlinx.coroutines.launch
import java.math.BigDecimal

@Composable
fun FornecedorScreen(
    navController: NavController?,
    fornecedorId: Long?,
) {
    val fornecedorViewModel: FornecedorViewModel = viewModel()
    val fornecedor by fornecedorViewModel.fornecedorSelecionado
    val userViewModel: UserViewModel = viewModel()
    val isFavorite = remember { mutableStateOf(false) }

    LaunchedEffect(fornecedorId) {
        fornecedorId?.let {
            Log.d("FornecedorScreen", "Calling getFornecedorById with ID: $it")
            fornecedorViewModel.getFornecedorById(it)
        }
    }

    when (fornecedor) {
        null -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        else -> {
            fornecedor?.let {
                FornecedorContent(it, navController, isFavorite, userViewModel)
            }
        }
    }
}

@Composable
fun FornecedorContent(
    fornecedor: Fornecedor,
    navController: NavController?,
    isFavorite: MutableState<Boolean>,
    userViewModel: UserViewModel
) {
    val productViewModel: ProdutoViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            AsyncImage(
                model = fornecedor.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
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
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopEnd)
                    .background(Color.White, CircleShape)
                    .size(32.dp)
                    .clickable {
                        if (isFavorite.value) {
                            fornecedor.id?.let {
                                userViewModel.removeFornecedorFavorito(it)
                            }
                        } else {
                            fornecedor.id?.let {
                                userViewModel.addFornecedorFavorito(it)
                            }
                        }
                    }
            ) {
                Icon(
                    imageVector = if (isFavorite.value)
                        Icons.Default.Favorite
                    else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = fornecedor.image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = fornecedor.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Reprehenderit posuere rerum aliquip possimus aptent alias quos sint nisl morbi autem. Sequi luctus sapien ultricies! Duis.",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
        Text(
            text = "Produtos",
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        val produtos = listOf(
            Produto(
                id = 1,
                nome = "Cesta extra grande",
                descricao = "Reprehenderit posuere rerum aliquip possimus aptent alias quos sint nisl morbi autem.",
                valor = BigDecimal(70.0),
                imagem = "https://www.health.com/thmb/fbyHcuD2A3OrfZTC-LUJIPsKKVk=/2121x0/filters:no_upscale():max_bytes(150000):strip_icc()/HealthiestFruits-feb2318dc0a3454993007f57c724753f.jpg",
                usuarioId = null
            ),
            Produto(
                id = 2,
                nome = "Cesta grande",
                descricao = "Reprehenderit posuere rerum aliquip possimus aptent alias quos sint nisl morbi autem.",
                valor = BigDecimal(55.0),
                imagem = "https://food.fnr.sndimg.com/content/dam/images/food/fullset/2022/06/23/fn_fruit-bowl-healthy-getty_s4x3.jpg.rend.hgtvcom.1280.960.suffix/1655995735443.jpeg",
                usuarioId = null
            ),
            Produto(
                id = 3,
                nome = "Cesta média",
                descricao = "Reprehenderit posuere rerum aliquip possimus aptent alias quos sint nisl morbi autem.",
                valor = BigDecimal(40.0),
                imagem = "https://kidlingoo.com/wp-content/uploads/vegetables_name_in_english_50.jpg",
                usuarioId = null
            ),
            Produto(
                id = 4,
                nome = "Cesta pequena",
                descricao = "Reprehenderit posuere rerum aliquip possimus aptent alias quos sint nisl morbi autem.",
                valor = BigDecimal(25.0),
                imagem = "https://4.imimg.com/data4/AY/TE/IMOB-27708947/c__data_users_defapps_appdata_internetexplorer_temp_sav.jpg",
                usuarioId = null
            ),
        )
        val coroutineScope = rememberCoroutineScope()
        produtos.forEach { produto ->
            LaunchedEffect(produto) {
                coroutineScope.launch {
                    productViewModel.salvar(produto)
                }
            }
            ListaProdutoItem(produto = produto, onClick = {
                navController?.navigate("detalhesProdutoScreen/${produto.id}/${fornecedor.id}")
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FornecedorScreenPreview() {
    FornecedorScreen(navController = null, null)
}