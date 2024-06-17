package com.example.sismocontrol.presentation.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sismocontrol.databinding.ActivityMainBinding
import com.example.sismocontrol.data.model.Sismo
import com.example.sismocontrol.data.repository.MainArepositoryImpl
import com.example.sismocontrol.domain.SismoUseCase
import com.example.sismocontrol.presentation.viewmodel.MainViewModel
import com.example.sismocontrol.presentation.viewmodel.MainViewModelFactory
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository:MainArepositoryImpl = MainArepositoryImpl()
        val sismoUseCase: SismoUseCase = SismoUseCase(repository)

        val viewModelFactory = MainViewModelFactory(sismoUseCase)

        val mainViewModel = ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]


        val sismoAdaptador = SismoAdapter()
        binding.recyclerSismos.layoutManager = LinearLayoutManager(this)
        binding.recyclerSismos.adapter = sismoAdaptador

        mainViewModel.sismosListLV.observe(this, Observer {
            sismoAdaptador.sismos = it
            sismoAdaptador.onItemClickListener = { sismo ->
                //enviarCorreoElectronicoSismo(sismo)
                enviarMensajeWhatSapp(sismo)
                if(sismoAdaptador.sismos.isEmpty())
                    binding.empty.visibility = View.VISIBLE
                else
                    binding.empty.visibility = View.GONE
            }

        })

       // initAdapter()
    }

    private fun initAdapter(){

    }

    // Implmentación de un Intent Implícito
    /**
     *     PREGUNTA DE EXAMEN DE CERTIFICACIÓN
     */
    private fun enviarCorreoElectronicoSismo(sismo: Sismo) {
           val intent = Intent(Intent.ACTION_SEND)
           intent.type = "message/rfc822"
           intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("agus.romero.salazar@gmail.com"))
           intent.putExtra(Intent.EXTRA_SUBJECT,"Envío de Sismo")
           intent.putExtra(
               Intent.EXTRA_TEXT,"Hola Agustín, acaba de haber un temblor en: " +
                   sismo.lugar + " de magnitud ${ sismo.magnitud } ")
           if(intent.resolveActivity(packageManager) != null ){
               startActivity(Intent.createChooser(intent, "Enviar por correo Sismo"))
           } else
               Toast.makeText(
                   this,
                   "Tienes que tener instalada una aplicación de correo",
                   Toast.LENGTH_LONG
               ).show()
    }

    private fun enviarMensajeWhatSapp(sismo: Sismo){

        val telefonoWsp = "+56952593215"
        val mensaje = "Hola Frank, acaba de haber un temblor en: " +
        sismo.lugar + " de magnitud ${ sismo.magnitud } "

        val uri = Uri.parse(
            "https://api.whatsapp.com/send?phone=$telefonoWsp&text=${
                URLEncoder.encode(
                    mensaje,
                    "UTF-8"
                )
            }"
        )
        val intent = Intent(Intent.ACTION_VIEW, uri)

        if(intent.resolveActivity(packageManager) != null ){
            startActivity(Intent.createChooser(intent, "Enviar por correo Sismo"))
        } else
            Toast.makeText(
                this,
                "Tienes que tener instalada una aplicación de correo",
                Toast.LENGTH_LONG
            ).show()
    }

}