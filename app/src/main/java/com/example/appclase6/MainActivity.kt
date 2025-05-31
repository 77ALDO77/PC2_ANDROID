package com.example.appclase6

import android.content.Intent
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appclase6.controlador.DocenteController
import com.example.appclase6.modelos.Docente
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var txtNombre:TextInputEditText
    private lateinit var txtPaterno:TextInputEditText
    private lateinit var txtMaterno:TextInputEditText
    private lateinit var txtSueldo:TextInputEditText
    private lateinit var txtHijos:TextInputEditText
    private lateinit var atvSexo:AutoCompleteTextView
    private lateinit var btnGrabar:Button
    private lateinit var btnVolver:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //
        txtNombre=findViewById(R.id.tvNombre)
        txtPaterno=findViewById(R.id.txtPaterno)
        txtMaterno=findViewById(R.id.txtMaterno)
        txtSueldo=findViewById(R.id.txtSueldo)
        txtHijos=findViewById(R.id.txtHijos)
        atvSexo=findViewById(R.id.atvSexo)
        btnGrabar=findViewById(R.id.btnGrabar)
        btnVolver=findViewById(R.id.btnVolver)
        //
        btnGrabar.setOnClickListener {
            //leer controles
            var nom=txtNombre.text.toString()
            var pat=txtPaterno.text.toString()
            var mat=txtMaterno.text.toString()
            var sue=txtSueldo.text.toString().toDouble()
            var hijos=txtHijos.text.toString().toInt()
            var sexo=atvSexo.text.toString()
            //crear objeto de la clase Docente
            var obj=Docente(0,nom,pat,mat,sue,hijos,sexo,"")
            //invocar al método save
            var salida=DocenteController().save(obj)
            //validar salida
            if(salida>0)
                showAlert("Docente registrado")
            else
                showAlert("Error en el registro")
        }
        btnVolver.setOnClickListener {
            var intent= Intent(this,ListadoDocenteActivity::class.java)
            startActivity(intent)
        }
    }
    fun showAlert(men:String){
        val builder=AlertDialog.Builder(this)
        builder.setTitle("Sistema")
        builder.setMessage(men)
        builder.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog=builder.create()
        dialog.show()
    }

}