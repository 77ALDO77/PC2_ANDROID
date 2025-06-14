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

class DatosSupervisorActivity : AppCompatActivity() {
    private lateinit var txtCodigo:TextInputEditText
    private lateinit var txtNombre:TextInputEditText
    private lateinit var txtPaterno:TextInputEditText
    private lateinit var txtMaterno:TextInputEditText
    private lateinit var txtSueldo:TextInputEditText
    private lateinit var atvTurno:AutoCompleteTextView
    private lateinit var imgDocenteDatos:Button
    private lateinit var btnModificar:Button
    private lateinit var btnVolver:Button
    private lateinit var btnEliminarDocente:Button
    private var imageUri: Uri? = null
    private val PICK_IMAGE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.supervisor_datos_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //referenciar
        txtCodigo=findViewById(R.id.tvCodigo)
        txtNombre=findViewById(R.id.txtNombreDatos)
        txtPaterno=findViewById(R.id.txtPaternoDatos)
        txtMaterno=findViewById(R.id.txtMaternoDatos)
        txtSueldo=findViewById(R.id.txtSueldoDatos)
        atvTurno=findViewById(R.id.atvTurnoDatos)
        imgDocenteDatos=findViewById(R.id.imgSupervisorDatos)
        btnModificar=findViewById(R.id.btnModificarSupervisor)
        btnVolver=findViewById(R.id.btnVolverSupervisor)
        btnEliminarDocente=findViewById(R.id.btnEliminarSupervisor)
        //cargar la vista con los datos
        mostrarDatos()
        //
        imgDocenteDatos.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE)
        }
        btnModificar.setOnClickListener {
            //leer controles
            var cod=txtCodigo.text.toString().toInt()
            var nom=txtNombre.text.toString()
            var pat=txtPaterno.text.toString()
            var mat=txtMaterno.text.toString()
            var sue=txtSueldo.text.toString().toDouble()
            var turno=atvTurno.text.toString()
            val fotoUri = imageUri?.toString() ?: ""
            //crear objeto de la clase Docente
            var obj=Supervisor(cod,nom,pat,mat,sue,turno,fotoUri)
            //invocar al método update
            var salida=SupervisorController().actualizar(obj)
            //validar salida
            if(salida>0)
                showAlert("Docente actualizado")
            else
                showAlert("Error en la actualización")
        }
        btnVolver.setOnClickListener {
            var intent=Intent(this,ListadoSupervisorActivity::class.java)
            startActivity(intent)
        }
        btnEliminarDocente.setOnClickListener {
            //leer el código
            var cod=txtCodigo.text.toString().toInt()
            //invocar al método delete
            var salida=SupervisorController().eliminar(cod)
            //validar salida
            if(salida>0)
                showAlert("Docente eliminado")
            else
                showAlert("Error en la eliminación")
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
    //función para recuperar la clave "codigo"
    fun mostrarDatos(){
        var data=intent.extras!!
        //invocar a la función findById y enviar el código
        var obj=SupervisorController().findById(data.getInt("codigo"))
        //mostrar valores del objeto "obj" en las cajas
        txtCodigo.setText(obj.codigo.toString())
        txtNombre.setText(obj.nombre)
        txtPaterno.setText(obj.paterno)
        txtMaterno.setText(obj.materno)
        txtSueldo.setText(obj.sueldo.toString())
        atvTurno.setText(obj.turno,false)
        //agregar imagen en la variable nada mas
        // si el campo fotoUri tiene un valor válido, convertirlo a Uri y asignarlo
        // Convertir el valor del campo fotoUri a Uri (si existe)
        if (!obj.foto.isNullOrEmpty()) {
            imageUri = Uri.parse(obj.foto)
        }
    }




}