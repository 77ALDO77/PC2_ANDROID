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
import com.example.appclase6.controlador.SupervisorController
import com.example.appclase6.modelos.Supervisor
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var txtNombre: TextInputEditText
    private lateinit var txtPaterno: TextInputEditText
    private lateinit var txtMaterno: TextInputEditText
    private lateinit var txtSueldo: TextInputEditText

    private lateinit var atvTurno: AutoCompleteTextView
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
        atvTurno = findViewById(R.id.atvTurno)
        btnGrabar = findViewById(R.id.btnGrabar)
        btnVolver = findViewById(R.id.btnVolver)
        btnImagen = findViewById(R.id.imgSupervisorDatos)

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
            val turno = atvTurno.text.toString()
            val fotoUri = imageUri?.toString() ?: ""

            val obj = Supervisor(0, nom, pat, mat, sue,turno, fotoUri)
            val salida = SupervisorController().save(obj)
            if (salida > 0)
                showAlert("Supervisor registrado")
            else
                showAlert("Error en el registro")
        }

        btnVolver.setOnClickListener {
            val intent = Intent(this, ListadoSupervisorActivity::class.java)
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

    fun showAlert(mensajeAlerta: String) { //mensaje es un parametro: String (tipo de dato)
        val builder = AlertDialog.Builder(this) //crear variable builder para guardar una instancia de la clase AlertDialog
        builder.setTitle("Sistema")
        builder.setMessage(mensajeAlerta)
        builder.setPositiveButton("Aceptar") { dialog, which ->
            // Este código se ejecutará cuando el usuario presione "Aceptar"
            val intent = Intent(this, ListadoSupervisorActivity::class.java)
            startActivity(intent)
            // Opcional: si quieres que la Activity actual se cierre después de ir a la nueva
            // finish()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}

// Comentario de prueba por Sara