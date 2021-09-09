package com.arcanesecurity.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import com.arcanesecurity.coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            val retorno = callServer()
            retorno.forEach {
                binding.textView.text = it
                delay(2000)
            }

        }


    }

    suspend fun callServer(): List<String> {
        println("......Antes de disparar a coroutine......")
        val listOfNames = withContext(Dispatchers.Default) {
            delay(3000)
            listOf("Arthur", "Botao", "Jaime", "Leonardo")
        }
        println("......Após disparar a coroutine e ter esperado 3 segundos......")
        return listOfNames
    }

}

