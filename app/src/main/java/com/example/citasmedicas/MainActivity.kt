
package com.example.citasmedicas

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

data class Cita(
    val nombre: String = "",
    val fecha: String = "",
    val hora: String = "",
    val motivo: String = ""
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGuardar.setOnClickListener {
            val nombre = editNombre.text.toString()
            val fecha = editFecha.text.toString()
            val hora = editHora.text.toString()
            val motivo = editMotivo.text.toString()

            val cita = Cita(nombre, fecha, hora, motivo)
            val db = FirebaseDatabase.getInstance().getReference("citas")
            val id = db.push().key

            if (id != null) {
                db.child(id).setValue(cita).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Cita guardada", Toast.LENGTH_SHORT).show()
                        editNombre.text.clear()
                        editFecha.text.clear()
                        editHora.text.clear()
                        editMotivo.text.clear()
                    } else {
                        Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
