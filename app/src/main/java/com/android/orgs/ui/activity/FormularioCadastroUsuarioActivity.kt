package com.android.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.orgs.database.OrgsAppDatabase
import com.android.orgs.databinding.ActivityFormularioCadastroUsuarioBinding
import com.android.orgs.extensions.toast
import com.android.orgs.model.Usuario
import kotlinx.coroutines.launch

class FormularioCadastroUsuarioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioCadastroUsuarioBinding.inflate(layoutInflater)
    }

    private val usuarioDao by lazy {
        OrgsAppDatabase.instancia(this).usuarioDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoCadastrar()
    }

    private fun configuraBotaoCadastrar() {
        binding.activityFormularioCadastroBotaoCadastrar.setOnClickListener {
//            val novoUsuario = criaUsuario()
//            cadastraUsuario(novoUsuario)
        }
    }

    private fun cadastraUsuario(usuario: Usuario) {
        lifecycleScope.launch {
            try {
                usuarioDao.salva(usuario)
                finish()
            } catch (e: Exception) {
                Log.i("CadastroUsuario", "configuraBotaoSalvar: $e")
                toast("Falha ao cadastrar usuário")
            }
        }
    }

//    private fun criaUsuario(): Usuario {
//        val usuario = binding.activityFormularioCadastroUsuario.text.toString()
//        val nome = binding.activityFormularioCadastroNome.text.toString()
//        val senha = binding.activityFormularioCadastroSenha.text.toString()
//        return Usuario(usuario, nome, senha)
//    }
}