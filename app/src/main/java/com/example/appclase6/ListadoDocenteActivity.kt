package com.example.appclase6

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appclase6.adaptador.DocenteAdapter
import com.example.appclase6.controlador.DocenteController
import com.example.appclase6.utils.SwipeToDeleteCallback

class ListadoDocenteActivity : AppCompatActivity() {
    private lateinit var rvDocentes: RecyclerView
    private lateinit var btnNuevo: Button
    private lateinit var adapter: DocenteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listado_docente_main)

        // Inicializar vistas
        rvDocentes = findViewById(R.id.rvDocentes)
        btnNuevo = findViewById(R.id.btnNuevoDocente)

        // Configurar RecyclerView
        setupRecyclerView()
        // Configurar botones
        setupButtons()
    }


    private fun setupRecyclerView() {
        // Crear adapter con datos
        // En ListadoDocenteActivity.kt
        val todosLosDocentes = ArrayList(DocenteController().findAll()) // Primero obtenemos la lista
        adapter = DocenteAdapter(docentes = todosLosDocentes, data = todosLosDocentes) // Luego la pasamos a ambos parámetros
        //hola
        // Configurar RecyclerView
        rvDocentes.apply {
            layoutManager = LinearLayoutManager(this@ListadoDocenteActivity)
            adapter = this@ListadoDocenteActivity.adapter
            addItemDecoration(
                DividerItemDecoration(
                    this@ListadoDocenteActivity,
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