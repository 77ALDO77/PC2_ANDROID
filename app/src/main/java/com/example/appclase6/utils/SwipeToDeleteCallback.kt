package com.example.appclase6.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.appclase6.R
import com.example.appclase6.adaptador.DocenteAdapter
import android.widget.Toast
import com.example.appclase6.controlador.DocenteController

class SwipeToDeleteCallback(
    private val adapter: DocenteAdapter,
    private val context: Context,
    dragDirs: Int = 0,
    swipeDirs: Int = ItemTouchHelper.RIGHT
) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    private val deleteIcon = ContextCompat.getDrawable(context, R.drawable.delete)
    private val background = ColorDrawable(Color.RED)

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val docente = adapter.getItem(position)

        AlertDialog.Builder(context)
            .setTitle("Confirmar eliminación")
            .setMessage("¿Estás seguro que deseas eliminar al docente ${docente.nombre} ${docente.paterno}?")
            .setPositiveButton("Eliminar") { _, _ ->
                val resultado = DocenteController().eliminar(docente.codigo)
                if (resultado > 0) {
                    adapter.removeItem(position)
                    Toast.makeText(context, "Docente eliminado correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    adapter.notifyItemChanged(position)
                    Toast.makeText(context, "Error al eliminar docente", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar") { _, _ ->
                adapter.notifyItemChanged(position)
            }
            .setCancelable(false)
            .show()
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        val itemHeight = itemView.bottom - itemView.top

        background.setBounds(
            itemView.left,
            itemView.top,
            itemView.right + dX.toInt(),
            itemView.bottom
        )
        background.draw(c)

        deleteIcon?.let {
            val iconMargin = (itemHeight - it.intrinsicHeight) / 2
            val iconTop = itemView.top + iconMargin
            val iconBottom = iconTop + it.intrinsicHeight
            val iconLeft = itemView.left + iconMargin
            val iconRight = iconLeft + it.intrinsicWidth

            it.setBounds(iconLeft, iconTop, iconRight, iconBottom)
            it.draw(c)
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}