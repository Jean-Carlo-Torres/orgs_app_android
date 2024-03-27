package com.android.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.android.orgs.databinding.FormularioImagemBinding
import com.android.orgs.extensions.tentaCarregarImagem

class FormularioImagemDialog(val context: Context) {
    fun mostrarDialog(quandoImagemCarregada: (imagem: String) -> Unit) {
        val binding = FormularioImagemBinding.inflate(LayoutInflater.from(context))
        binding.formularioImagemBotaoCarregar.setOnClickListener {
            val url = binding.formularioImagemUrl.text.toString()
            binding.formularioImagemImagemview.tentaCarregarImagem(url)
        }
        AlertDialog.Builder(context)
            .setTitle("Inserir Imagem")
            .setView(binding.root)
            .setPositiveButton("Ok") { _, _ ->
                val url = binding.formularioImagemUrl.text.toString()
                quandoImagemCarregada(url)
            }
            .setNegativeButton("Cancelar") { _, _ ->

            }
            .show()
    }
}