package com.venanciob.dam.pmdm.miprimerapp

import android.content.Intent
import com.venanciob.dam.pmdm.miprimerapp.R
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.venanciob.dam.pmdm.miprimerapp.databinding.ActivityMainBinding
private const val TAG = "CicloVida"


class MainActivity : AppCompatActivity() {

    companion object {
        // Clave para guardar el contador en el Bundle
        private const val CLAVE_CONTADOR = "contador"
    }
    // Clase generada por View Binding a partir de activity_main.xml
    private lateinit var binding: ActivityMainBinding

    // Estado de la pantalla: el valor actual del contador
    private var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contador = savedInstanceState?.getInt(CLAVE_CONTADOR) ?: 0
        mostrarContador()

        // Inflar el layout con View Binding en lugar de setContentView(R.layout...)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Código de la plantilla, adaptado a View Binding (binding.root es la vista "main")
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Mostrar el valor inicial
        mostrarContador()

        // Eventos: qué hacer cuando se pulsa cada botón
        binding.btnSumar.setOnClickListener {
            contador++
            mostrarContador()
        }
        binding.btnRestar.setOnClickListener {
            if (contador > 0) {
                contador--
            }
            mostrarContador()
        }

        binding.btnReiniciar.setOnClickListener {
            contador=0
            mostrarContador()
        }
        binding.btnDetalle.setOnClickListener{
            startActivity(Intent(this, DetallesActivity::class.java))
        }

    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CLAVE_CONTADOR, contador)
    }

    // Un único sitio que actualiza la interfaz a partir del estado
    private fun mostrarContador() {
        binding.tvContador.text = contador.toString()
        if (contador > 10) {
            binding.tvContador.setTextColor(getColor(R.color.rojo))
        }else {
            binding.tvContador.setTextColor(getColor(R.color.black))
        }
    }
}
