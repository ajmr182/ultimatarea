package com.example.tareafraseamilcarm

import android.content.Context
import android.hardware.input.InputManager
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.tareafraseamilcarm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var frase: String
    private var cantidadEspacios = 0
    private var cantidadNumeros = 0
    private var minusculas = 0
    private var mayusculas = 0
    private var cantidadComas = 0
    private var cantidadPuntos = 0
    private var caracterDesconocido = 0
    private lateinit var frasePrueba: String
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.btnCalc.setOnClickListener {

            val imm=baseContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.etFrase.applicationWindowToken,0)
            calc()
            val cantidadCaracteres = frase.length
            eliminarNumeros()
            val palabra = frasePrueba.trim().split(" ").size
            binding.tvCantidadNumeros.text = getString(R.string.cantidad_digitos, cantidadNumeros)
            binding.tvMayusculas.text = getString(R.string.cantidad_mayusculas, mayusculas)
            binding.tvMinusculas.text = getString(R.string.cantidad_minusculas, minusculas)
            binding.tvCantidadEspacios.text = getString(R.string.cantidad_espacios, cantidadEspacios)
            binding.tvCantidadPuntos.text = getString(R.string.cantidad_puntos, cantidadPuntos)
            binding.tvCantidadComas.text = getString(R.string.cantidad_comas, cantidadComas)
            binding.tvLongitud.text = getString(R.string.longitud, cantidadCaracteres)
            binding.tvCantidadPalabras.text = getString(R.string.cantidad_palabras, palabra)
            binding.tvDesconocido.text = getString(R.string.desconocido, caracterDesconocido)
        }
        binding.btnClean.setOnClickListener {

            borrarCajas()
        }
        setContentView(binding.root)
    }

    private fun View.hideKeybord() {

    }

    private fun calc() {

        frase = binding.etFrase.text.toString().trim()
        for (i in frase.indices) {

            when {

                frase[i].isWhitespace() -> {

                    cantidadEspacios++
                }
                frase[i].isDigit() -> {

                    cantidadNumeros++
                }
                frase[i].isLowerCase() -> {

                    minusculas++
                }
                frase[i].isUpperCase() -> {

                    mayusculas++
                }
                frase[i].toString() == "," -> {
                    cantidadComas++
                }
                frase[i].toString() == "." -> {
                    cantidadPuntos++
                }
                else -> {
                    caracterDesconocido++
                }
            }
        }
    }

    private fun eliminarNumeros() {

        frasePrueba = frase.replace("0", "").replace("1", "")
            .replace("2", "").replace("3", "")
            .replace("4", "").replace("5", "")
            .replace("6", "").replace("7", "")
            .replace("8", "").replace("9", "").replace("\\s+".toRegex(), " ")
    }
    private fun borrarCajas() {

        binding.etFrase.setText("")
        binding.tvCantidadNumeros.text = ""
        binding.tvMayusculas.text = ""
        binding.tvMinusculas.text = ""
        binding.tvCantidadEspacios.text = ""
        binding.tvCantidadPuntos.text = ""
        binding.tvCantidadComas.text = ""
        binding.tvLongitud.text = ""
        binding.tvCantidadPalabras.text = ""
        binding.tvDesconocido.text = ""
        cantidadEspacios = 0
        cantidadNumeros = 0
        minusculas = 0
        mayusculas = 0
        cantidadComas = 0
        cantidadPuntos = 0
        caracterDesconocido = 0
    }
}


