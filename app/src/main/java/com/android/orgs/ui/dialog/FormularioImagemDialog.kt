package com.android.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.android.orgs.R
import com.android.orgs.databinding.FormularioImagemBinding
import com.android.orgs.extensions.tentaCarregarImagem

class FormularioImagemDialog(val context: Context) {
    fun mostrarDialog(
        urlPadrao: String? = null,
        quandoImagemCarregada: (imagem: String) -> Unit
    ) {
        FormularioImagemBinding
            .inflate(LayoutInflater.from(context)).apply {
                urlPadrao?.let {
                    formularioImagemImagemview.tentaCarregarImagem(it)
                    formularioImagemUrl.setText(it)
                }
                formularioImagemBotaoCarregar.setOnClickListener {
                    val url = formularioImagemUrl.text.toString()
                    formularioImagemImagemview.tentaCarregarImagem(url)
                }
                AlertDialog.Builder(context)
                    .setTitle(context.resources.getString(R.string.text_inserir_imagem))
                    .setView(root)
                    .setPositiveButton("Ok") { _, _ ->
                        val url = formularioImagemUrl.text.toString()
                        quandoImagemCarregada(url)
                    }
                    .setNegativeButton(context.resources.getString(R.string.text_cancelar)) { _, _ ->

                    }
                    .show()
            }
    }
}