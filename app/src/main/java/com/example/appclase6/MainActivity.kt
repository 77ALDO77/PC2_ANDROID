package com.example.appclase6

import android.app.Activity
import android.content.Intent
import android.net.Uri
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
    private lateinit var txtNombre: TextInputEditText
    private lateinit var txtPaterno: TextInputEditText
    private lateinit var txtMaterno: TextInputEditText
    private lateinit var txtSueldo: TextInputEditText
    private lateinit var txtHijos: TextInputEditText
    private lateinit var atvSexo: AutoCompleteTextView
    private lateinit var btnGrabar: Button
    private lateinit var btnVolver: Button
    private lateinit var btnImagen: Button

    private var imageUri: Uri? = null
    private val PICK_IMAGE = 1001

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
        txtNombre = findViewById(R.id.tvNombre)
        txtPaterno = findViewById(R.id.txtPaterno)
        txtMaterno = findViewById(R.id.txtMaterno)
        txtSueldo = findViewById(R.id.txtSueldo)
        txtHijos = findViewById(R.id.txtHijos)
        atvSexo = findViewById(R.id.atvSexo)
        btnGrabar = findViewById(R.id.btnGrabar)
        btnVolver = findViewById(R.id.btnVolver)
        btnImagen = findViewById(R.id.imgDocente)

        btnImagen.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE)
        }

        btnGrabar.setOnClickListener {
            val nom = txtNombre.text.toString()
            val pat = txtPaterno.text.toString()
            val mat = txtMaterno.text.toString()
            val sue = txtSueldo.text.toString().toDoubleOrNull() ?: 0.0
            val hijos = txtHijos.text.toString().toIntOrNull() ?: 0
            val sexo = atvSexo.text.toString()
            val fotoUri = imageUri?.toString() ?: ""

            val obj = Docente(0, nom, pat, mat, sue, hijos, sexo, fotoUri)
            val salida = DocenteController().save(obj)
            if (salida > 0)
                showAlert("Docente registrado")
            else
                showAlert("Error en el registro")
        }

        btnVolver.setOnClickListener {
            val intent = Intent(this, ListadoDocenteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data
            // Aquí podrías mostrar la imagen seleccionada en un ImageView si lo deseas
        }
    }

    fun showAlert(men: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sistema")
        builder.setMessage(men)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}