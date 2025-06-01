package com.example.appclase6.adaptador

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appclase6.DatosSupervisorActivity
import com.example.appclase6.R
import com.example.appclase6.modelos.Supervisor
import com.example.appclase6.vistas.VistaSupervisor

class SupervisorAdapter(private val supervisors: List<Supervisor>, private val data: List<Supervisor>) :
    RecyclerView.Adapter<VistaSupervisor>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaSupervisor {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_supervisor, parent, false)
        return VistaSupervisor(vista)
    }

    override fun onBindViewHolder(holder: VistaSupervisor, position: Int) {
        val supervisor = supervisors[position]

        holder.tvNombre.text = supervisor.nombre
        holder.tvPaterno.text = supervisor.paterno
        holder.tvMaterno.text = supervisor.materno

        if (supervisor.foto.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(supervisor.foto)
                .placeholder(R.drawable.account)
                .into(holder.imgFoto)
        } else {
            holder.imgFoto.setImageResource(R.drawable.account)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DatosSupervisorActivity::class.java)
            intent.putExtra("codigo", supervisor.codigo)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = supervisors.size

    fun getItem(position: Int): Supervisor = supervisors[position]

    fun removeItem(position: Int) {
        (supervisors as MutableList).removeAt(position)
        notifyItemRemoved(position)
    }
}