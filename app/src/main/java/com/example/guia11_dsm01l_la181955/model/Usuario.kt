package com.example.guia11_dsm01l_la181955.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.guia11_dsm01l_la181955.db.HelperDB

class Usuario(context: Context?) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.getWritableDatabase()
    }

    companion object {
        // Nombre de tabla usuarios
        val TABLE_NAME_USUARIOS = "usuarios"
        // Campos de tabla usuarios
        val COL_ID = "idusuario"
        val COL_USUARIO = "usuario"
        val COL_PASSWORD = "password"

        // Sentencia para crear la tabla
        val CREATE_TABLE_USUARIOS = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USUARIOS + "("
                        + COL_ID + " integer primary key autoincrement,"
                        + COL_USUARIO + " varchar(50) NOT NULL,"
                        + COL_PASSWORD + " varchar(255) NOT NULL);"
                )
    }

    fun generarContentValues(
      usuario: String,
      password: String
    ): ContentValues? {
        val valores = ContentValues()
        valores.put(COL_USUARIO, usuario)
        valores.put(COL_PASSWORD, password)
        return valores
    }

    fun registrarUsuario(usuario: String, password: String) {
        db!!.insert(
            TABLE_NAME_USUARIOS,
            null,
            generarContentValues(usuario, password)
        )
    }

    fun obtenerUsuario(usuario: String, password: String): Boolean {
        val columns = arrayOf(COL_ID, COL_USUARIO, COL_PASSWORD)
        var cursor: Cursor? = db!!.query(
            TABLE_NAME_USUARIOS, columns,
            "${COL_USUARIO} = ? AND ${COL_PASSWORD} = ?", arrayOf(usuario, password), null, null, null
        )

        // Si se cumple es porque se encontró el usuario en la base de datos
        if (cursor != null && cursor!!.count > 0) {
            return true
        }
        return false
    }
}