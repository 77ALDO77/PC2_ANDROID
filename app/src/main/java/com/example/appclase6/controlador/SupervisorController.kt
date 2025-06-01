package com.example.appclase6.controlador

import android.content.ContentValues
import com.example.appclase6.modelos.Supervisor
import com.example.appclase6.utils.AppConfig

class SupervisorController {
    //crear una función que retorne un arreglo de objetos
    //de la clase Docente
    fun findAll():ArrayList<Supervisor>{
        //crear arreglo
        var lista=ArrayList<Supervisor>()
        //acceder a la base de datos "consorcio.bd" en modo lectura
        var conn=AppConfig.CN.readableDatabase
        //senetencia SQL
        var rs=conn.rawQuery("select *from tb_supervisor",null)
        //bucle para realizar recorrido sobre rs
        while(rs.moveToNext()){
         //crear objeto de la clase Docente con los valores actuales de rs
         var bean=Supervisor(
             rs.getInt(0),
             rs.getString(1),
             rs.getString(2),
             rs.getString(3),
             rs.getDouble(4),
             rs.getString(5),
             rs.getString(6))
        //adicionar objeto bean en el arreglo lista
        lista.add(bean)
        }
        return lista
    }
    //función para registrar Docente
    fun save(bean:Supervisor):Int{
        var salida=-1
        //acceso a la base de datos en modo escritura
        var conn=AppConfig.CN.writableDatabase
        //crear un objeto de la clase ContentValues con los valores del objeto "bean"
        var valores=ContentValues()
        //clave"campo de la tabla" -> valor
        valores.put("nom",bean.nombre)
        valores.put("pat",bean.paterno)
        valores.put("mat",bean.materno)
        valores.put("sue",bean.sueldo)
        valores.put("turno",bean.turno)
        valores.put("foto",bean.foto)
        //método para insertar
        salida=conn.insert("tb_supervisor","cod",valores).toInt()

        return salida
    }
    fun findById(cod:Int):Supervisor{
        //declarar objeto de la clase Docente
        lateinit var bean:Supervisor
        //acceder a la base de datos en modo lectura
        var conn=AppConfig.CN.readableDatabase
        //sentencia sql
        var sql="select *from tb_supervisor where cod=?"
        //ejecutar sentencia sql
        var rs=conn.rawQuery(sql, arrayOf(cod.toString()))
        //validar si hay registro
        if(rs.moveToNext()){
          //crear objeto bean
          bean=Supervisor(
              rs.getInt(0),
              rs.getString(1),
              rs.getString(2),
              rs.getString(3),
              rs.getDouble(4),
              rs.getString(5),
              rs.getString(6))
        }
        return bean
    }
    fun actualizar(bean:Supervisor):Int{
        var salida=-1
        //acceso a la base de datos en modo escritura
        var conn=AppConfig.CN.writableDatabase
        //crear objeto de la clase ContentValues
        //update tb_docente set campo1=valor1, campo2=valor2 ... where cod=?
        var data=ContentValues()
        //asignar claves(campos de la tb_docente) con los valores
        data.put("nom",bean.nombre)
        data.put("pat",bean.paterno)
        data.put("mat",bean.materno)
        data.put("sue",bean.sueldo)
        data.put("turno",bean.turno)
        data.put("foto",bean.foto)
        //invocar al metodo update
        salida=conn.update("tb_supervisor",data,"cod=?", arrayOf(bean.codigo.toString()))
        return salida;
    }
    fun eliminar(cod:Int):Int{
        var salida=-1
        var conn=AppConfig.CN.writableDatabase
        salida=conn.delete("tb_supervisor","cod=?", arrayOf(cod.toString()))
        return salida
    }


}