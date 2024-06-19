package com.android.orgs.ui.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.android.orgs.databinding.ActivityPerfilUsuarioBinding
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class PerfilUsuarioActivity : UsuarioBaseActivity() {

    private val binding by lazy {
        ActivityPerfilUsuarioBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        preencheCamposUsuario()
        configuraBotaoSair()
    }

    private fun preencheCamposUsuario() {
        lifecycleScope.launch {
            usuario.filterNotNull().collect { usuarioLogado ->
//                binding.perfilUsuarioId.text = usuarioLogado.id
                binding.perfilUsuarioUsuario.text = usuarioLogado.nome
            }
        }
    }

    private fun configuraBotaoSair() {
        binding.perfilUsuarioBotaoSair.setOnClickListener {
            lifecycleScope.launch {
                deslogaUsuario()
            }
        }
    }
}