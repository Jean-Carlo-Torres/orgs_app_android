package com.android.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.orgs.databinding.ActivityTelaInicialDeslogadoBinding
import com.android.orgs.extensions.vaiPara

class TelaInicialDeslogadoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityTelaInicialDeslogadoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vaiParaLogin()
        configuraBotaoCadastrar()
    }

    private fun vaiParaLogin() {
        binding.activityInicialDeslogadoBotaoLogin.setOnClickListener {
            vaiPara(LoginActivity::class.java)
        }
    }

    private fun configuraBotaoCadastrar() {
        binding.activityLoginBotaoCadastrar.setOnClickListener {
            vaiPara(FormularioCadastroUsuarioActivity::class.java)
        }
    }
}