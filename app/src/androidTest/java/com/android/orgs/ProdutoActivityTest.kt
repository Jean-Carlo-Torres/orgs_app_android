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
import com.android.orgs.ui.activity.FormularioProdutoActivity
import com.android.orgs.ui.activity.ListaProdutosActivity
import org.junit.Test

class ProdutoActivityTest {

    @Test
    fun deveMostrarONomeDoAplicativo() {
        launch(ListaProdutosActivity::class.java)
        onView(withText("Orgs"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun deveMostrarCamposNecessariosParaCriarUmProduto() {
        launch(FormularioProdutoActivity::class.java)
        onView(withId(R.id.activity_formulario_produto_nome))
        onView(withId(R.id.activity_formulario_produto_descricao))
        onView(withId(R.id.activity_formulario_produto_valor))
        onView(withId(R.id.activity_formulario_produto_botao_salvar))
            .check(matches(isDisplayed()))
    }

    @Test
    fun deveSalvarProdutoComSucesso() {
        launch(FormularioProdutoActivity::class.java)
        onView(withId(R.id.activity_formulario_produto_nome))
            .perform(
                typeText("teste"),
                closeSoftKeyboard()
            )
        onView(withId(R.id.activity_formulario_produto_descricao))
            .perform(
                typeText("teste"),
                closeSoftKeyboard()
            )
        onView(withId(R.id.activity_formulario_produto_valor))
            .perform(
                typeText("10"),
                closeSoftKeyboard()
            )
        onView(withId(R.id.activity_formulario_produto_botao_salvar))
            .perform(click())

        launch(ListaProdutosActivity::class.java)

        onView(withText("teste")).check(matches(isDisplayed()))
    }

}