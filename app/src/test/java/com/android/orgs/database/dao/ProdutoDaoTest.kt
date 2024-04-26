package com.android.orgs.database.dao

import com.android.orgs.database.dao.ProdutoDao
import com.android.orgs.model.Produto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.math.BigDecimal

class ProdutoDaoTest {

    @Test
    fun `deve chamar o dao quando salvar um produto`() = runBlocking {
        val dao = mockk<ProdutoDao>()
        val produto = Produto(
            nome = "teste",
            descricao = "teste",
            valor = BigDecimal("10.99")
        )

        coEvery {
            dao.salvar(produto)
        }.returns(Unit)

        runBlocking {
            dao.salvar(produto)
        }

        coVerify {
            dao.salvar(produto)
        }
    }
}