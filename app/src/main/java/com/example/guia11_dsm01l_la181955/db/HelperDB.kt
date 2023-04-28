package com.example.guia11_dsm01l_la181955.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.guia11_dsm01l_la181955.model.Categoria
import com.example.guia11_dsm01l_la181955.model.Producto
import com.example.guia11_dsm01l_la181955.model.Usuario

class HelperDB(context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "tienda.sqlite"
        private const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Categoria.CREATE_TABLE_CATEGORIA)
        db.execSQL(Producto.CREATE_TABLE_PRODUCTOS)
        db.execSQL(Usuario.CREATE_TABLE_USUARIOS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
}