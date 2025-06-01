package com.example.appclase6

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appclase6.adaptador.SupervisorAdapter
import com.example.appclase6.controlador.SupervisorController
import com.example.appclase6.utils.SwipeToDeleteCallback

class ListadoSupervisorActivity : AppCompatActivity() {
    private lateinit var rvDocentes: RecyclerView
    private lateinit var btnNuevo: Button
    private lateinit var adapter: SupervisorAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.listado_supervisor_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        // Inicializar vistas
        rvDocentes = findViewById(R.id.rvSupervisores)
        btnNuevo = findViewById(R.id.btnNuevoSupervisor)

        // Configurar RecyclerView
        setupRecyclerView()
        // Configurar botones
        setupButtons()
    }


    private fun setupRecyclerView() {
        // Crear adapter con datos
        // En ListadoDocenteActivity.kt
        val todosLosDocentes = ArrayList(SupervisorController().findAll()) // Primero obtenemos la lista
        adapter = SupervisorAdapter(supervisors = todosLosDocentes, data = todosLosDocentes) // Luego la pasamos a ambos parámetros
        //hola
        // Configurar RecyclerView
        rvDocentes.apply {
            layoutManager = LinearLayoutManager(this@ListadoSupervisorActivity)
            adapter = this@ListadoSupervisorActivity.adapter
            addItemDecoration(
                DividerItemDecoration(
                    this@ListadoSupervisorActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        // Configurar swipe para eliminar
        val swipeHandler = SwipeToDeleteCallback(adapter, this)
        ItemTouchHelper(swipeHandler).attachToRecyclerView(rvDocentes)
    }

    private fun setupButtons() {
        btnNuevo.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}