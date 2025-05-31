package com.example.appclase6.adaptador

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appclase6.DatosDocenteActivity
import com.example.appclase6.R
import com.example.appclase6.modelos.Docente
import com.example.appclase6.utils.AppConfig
import com.example.appclase6.vistas.VistaDocente

data class DocenteAdapter( val docentes: ArrayList<Docente>,var data:ArrayList<Docente>):
                RecyclerView.Adapter<VistaDocente>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaDocente {
        //obtener vinculo con item_docente.xml
        var item=LayoutInflater.from(parent.context).
                    inflate(R.layout.item_docente,parent,false)
        return VistaDocente(item)
    }
    override fun getItemCount(): Int {
        return data.size
    }
    override fun onBindViewHolder(holder: VistaDocente, position: Int) {
        //acceder a los atributos de holder para imprimir valores
        holder.tvCodigo.text=data.get(position).codigo.toString()
        holder.tvNombre.text=data.get(position).nombre
        holder.tvPaterno.text=data.get(position).paterno
        holder.tvMaterno.text=data.get(position).materno
        //asignar evento click al parámetro holder
        holder.itemView.setOnClickListener {
            //crear objeto de la clase Intent
            var intent=Intent(AppConfig.contexto,DatosDocenteActivity::class.java)
            //enviar clave dentro del objeto intent
            intent.putExtra("codigo",data.get(position).codigo)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
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