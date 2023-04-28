package com.example.guia11_dsm01l_la181955

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.guia11_dsm01l_la181955.db.HelperDB
import com.example.guia11_dsm01l_la181955.model.Usuario

class LoginActivity : AppCompatActivity() {
    private var managerUsuario: Usuario?= null
    private var dbHelper: HelperDB? = null
    private var db: SQLiteDatabase? = null
    private lateinit var btnLogin : Button
    private lateinit var btnRegistro : Button
    private lateinit var txtUsuario : EditText
    private lateinit var txtPassword : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        dbHelper = HelperDB(this)
        db = dbHelper!!.writableDatabase
        managerUsuario = Usuario(this)
        txtUsuario = findViewById<EditText>(R.id.txtUsuario)
        txtPassword = findViewById<EditText>(R.id.txtPassword)
        accionBtnLogin()
        accionBtnRegistro()
    }

    private fun validarCampos() : Int {
        var errores : Int = 0

        if (txtUsuario.text.toString().isEmpty()) {
            txtUsuario.setError("Ingrese su nombre de usuario.")
            errores += 1
        }

        if (txtPassword.text.toString().isEmpty()) {
            txtPassword.setError("Ingrese su contraseña.")
            errores += 1
        }
        return errores
    }

    private fun accionBtnLogin() {
        btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            var errores : Int = validarCampos()
            if (errores == 0) {
                val login : Boolean = managerUsuario!!.obtenerUsuario(txtUsuario.text.toString(), txtPassword.text.toString())
                if (login) {
                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Las credenciales ingresadas no corresponden a ningún usuario registrado.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun accionBtnRegistro() {
        btnRegistro = findViewById<Button>(R.id.btnRegistrar)

        btnRegistro.setOnClickListener {
            var errores : Int = validarCampos()
            if (errores == 0) {
                managerUsuario!!.registrarUsuario(txtUsuario.text.toString(), txtPassword.text.toString())
                Toast.makeText(this, "Registro realizado con éxito", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}