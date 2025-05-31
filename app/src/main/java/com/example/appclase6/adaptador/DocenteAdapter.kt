package com.example.appclase6.adaptador

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appclase6.DatosDocenteActivity
import com.example.appclase6.R
import com.example.appclase6.modelos.Docente
import com.example.appclase6.utils.AppConfig
import com.example.appclase6.vistas.VistaDocente

class DocenteAdapter(
    private val docentes: ArrayList<Docente>,
    var data: ArrayList<Docente>
) : RecyclerView.Adapter<VistaDocente>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaDocente {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_docente, parent, false)
        return VistaDocente(item)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: VistaDocente, position: Int) {
        val docente = data[position]
        holder.tvCodigo.text = docente.codigo.toString()
        holder.tvNombre.text = docente.nombre
        holder.tvPaterno.text = docente.paterno
        holder.tvMaterno.text = docente.materno

        // Cargar imagen con Glide
        Glide.with(holder.itemView.context)
            .load(docente.foto)
            .placeholder(R.drawable.account) // Usa tu recurso de imagen por defecto
            .error(R.drawable.account)
            .into(holder.imgFoto)

        holder.itemView.setOnClickListener {
            val intent = Intent(AppConfig.contexto, DatosDocenteActivity::class.java)
            intent.putExtra("codigo", docente.codigo)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            AppConfig.contexto.startActivity(intent)
        }
    }

    fun getItem(position: Int): Docente = docentes[position]

    fun removeItem(position: Int) {
        docentes.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, docentes.size)
    }
}