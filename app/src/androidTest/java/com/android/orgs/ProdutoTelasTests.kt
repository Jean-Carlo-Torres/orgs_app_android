package com.android.orgs

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.android.orgs.database.OrgsAppDatabase
import com.android.orgs.ui.activity.ListaProdutosActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProdutoTelasTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ListaProdutosActivity::class.java)

    @Before
    fun preparaAmbiente() {
        OrgsAppDatabase.instancia(InstrumentationRegistry.getInstrumentation().targetContext)
            .clearAllTables()
    }

    @Test
    fun deveMostrarONomeDoAplicativo() {
        onView(withText("Orgs"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun deveMostrarCamposNecessariosParaCriarUmProduto() {
        clicaNoFab()
        onView(withId(R.id.activity_formulario_produto_nome))
        onView(withId(R.id.activity_formulario_produto_descricao))
        onView(withId(R.id.activity_formulario_produto_valor))
        onView(withId(R.id.activity_formulario_produto_botao_salvar))
            .check(matches(isDisplayed()))
    }

    @Test
    fun deveSalvarProdutoComSucesso() {
        clicaNoFab()
        preencheCamposDoFormulario()

        launch(ListaProdutosActivity::class.java)

        onView(withText("teste nome")).check(matches(isDisplayed()))
    }

    @Test
    fun deveSerCapazDeEditarUmProduto() {
        clicaNoFab()
        preencheCamposDoFormulario()
        alteraProduto()
        clicaNoBotaoSalvar()

        onView(withText("teste alterado"))
            .check(matches(isDisplayed()))
    }

    private fun alteraProduto() {
        onView(withText("teste"))
            .perform(click())

        onView(withId(R.id.menu_detalhes_produto_editar))
            .perform(click())

        onView(withId(R.id.activity_formulario_produto_nome))
            .perform(
                typeText("teste alterado"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.activity_formulario_produto_descricao))
            .perform(
                typeText("teste alterado"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.activity_formulario_produto_valor))
            .perform(
                typeText("20"),
                closeSoftKeyboard()
            )
    }

    private fun preencheCamposDoFormulario() {
        onView(withId(R.id.activity_formulario_produto_nome))
            .perform(
                typeText("teste nome"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.activity_formulario_produto_descricao))
            .perform(
                typeText("teste descrição"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.activity_formulario_produto_valor))
            .perform(
                typeText("10"),
                closeSoftKeyboard()
            )

        clicaNoBotaoSalvar()
    }

    private fun clicaNoBotaoSalvar() {
        onView(withId(R.id.activity_formulario_produto_botao_salvar))
            .perform(click())
    }

    private fun clicaNoFab() {
        onView(withId(R.id.activity_lista_produtos_fab))
            .perform(click())
    }
}