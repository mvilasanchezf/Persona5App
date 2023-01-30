package com.example.personas.nuevaPersona.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personas.dbConection.db
import com.example.personas.dbConection.nombre_coleccion

class NuevaPersonaViewModel: ViewModel() {
    private val _mensaje_confirmacion = MutableLiveData<String>()
    val mensaje_confirmacion : LiveData<String> = _mensaje_confirmacion

    private val _numero_persona = MutableLiveData<String>()
    val numero_persona : LiveData<String> = _numero_persona

    private val _nombre_persona = MutableLiveData<String>()
    val nombre_persona : LiveData<String> = _nombre_persona

    private val _rol_persona = MutableLiveData<String>()
    val rol_persona : LiveData<String> = _rol_persona

    private val _elemento_persona = MutableLiveData<String>()
    val elemento_persona : LiveData<String> = _elemento_persona


    fun onCompletedFields(numero_persona:String, nombre_persona:String, rol_persona:String,
                          elemento_persona:String) {
        _numero_persona.value = numero_persona
        _nombre_persona.value = nombre_persona
        _rol_persona.value = rol_persona
        _elemento_persona.value = elemento_persona

    }

    fun emptyFields() {

        _numero_persona.value = ""
        _nombre_persona.value = ""
        _rol_persona.value = ""
        _elemento_persona.value = ""

    }

    fun insertarPersona(numero_persona:String, nombre_persona:String, rol_persona:String,
                        elemento_persona:String, dato:HashMap<String,String>){

        if (numero_persona.isNotBlank() && nombre_persona.isNotBlank() &&
            rol_persona.isNotBlank() && elemento_persona.isNotBlank()){

            db.collection(nombre_coleccion)
                .document(numero_persona)
                .set(dato)
                .addOnSuccessListener {
                    _mensaje_confirmacion.value = "La persona ha sido registrada correctamente"
                }
                .addOnFailureListener {
                    _mensaje_confirmacion.value = "Error al registrar la persona"
                }
        }else{
            _mensaje_confirmacion.value = "Debe completar todos los campos"
        }
    }
}