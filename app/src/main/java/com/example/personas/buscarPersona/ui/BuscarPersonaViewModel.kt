package com.example.personas.buscarPersona.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personas.dbConection.db
import com.example.personas.dbConection.nombre_coleccion

class BuscarPersonaViewModel: ViewModel() {

    private val _numero_persona = MutableLiveData<String>()
    val numero_persona : MutableLiveData<String> = _numero_persona

    private val _nombre_persona = MutableLiveData<String>()
    val nombre_persona : MutableLiveData<String> = _nombre_persona

    private val _rol_persona = MutableLiveData<String>()
    val rol_persona : MutableLiveData<String> = _rol_persona

    private val _elemento_persona = MutableLiveData<String>()
    val elemento_persona : MutableLiveData<String> = _elemento_persona

    private val _nombre_busqueda = MutableLiveData<String>()
    val nombre_busqueda : MutableLiveData<String> = _nombre_busqueda

    private val _datos = MutableLiveData<String>()
    val datos : MutableLiveData<String> = _datos

    private val _mensaje = MutableLiveData<String>()
    val mensaje : MutableLiveData<String> = _mensaje

    fun onCompletedFields(nombre_busqueda:String) {
        _nombre_busqueda.value = nombre_busqueda
    }

    fun buscarPersona(datos:String, nombre_busqueda: String ){

        //Vaciar la variable
        _datos.value = ""
        _numero_persona.value = ""
        _nombre_persona.value = ""
        _rol_persona.value = ""
        _elemento_persona.value = ""
        //Hacemos la busqueda a la coleccion con get
        db.collection(nombre_coleccion)
            .whereEqualTo("nombre_persona", nombre_busqueda)
            .get()

            //Si la busqueda es exitosa, se ejecuta el siguiente codigo
            .addOnSuccessListener { result ->
                for (encontrado in result) {
                    _datos.value += "${encontrado.id}: ${encontrado.data}\n"


                    _numero_persona.value = encontrado.data["numero_persona"].toString()
                    _nombre_persona.value = encontrado.data["nombre_persona"].toString()
                    _rol_persona.value = encontrado.data["rol_persona"].toString()
                    _elemento_persona.value = encontrado.data["elemento_persona"].toString()
                }

                if (datos.isEmpty()){
                    _mensaje.value = "No se ha encontrado a la persona"
                }else{
                    _mensaje.value = "Persona encontrada"
                }
            }
            //Si la busqueda no es exitosa, lanza el siguiente error
            .addOnFailureListener { exception ->
                _datos.value = "Error al buscar la persona"
            }

    }

}