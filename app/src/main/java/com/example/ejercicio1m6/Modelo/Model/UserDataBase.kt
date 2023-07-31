package com.example.ejercicio1m6.Modelo.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDataBase : RoomDatabase() {

    // conexion dao con la base de datos
    abstract fun getUserDao(): UserDao

    // NOSE PUEDE ISNTANCIAR ES ABSTRACTO
    // COMPANION OBJECT EXPONE UN OBJETO SIN INSTANCIAR LA CLASE
    companion object {
        // ESTA VARIABLE ESTE SIEMPRE DISPONIBLE
        @Volatile
        private var INSTANCE: UserDataBase? = null


        // MAIN THREAD
        // BACK THREAD HILO SECUNDARIOS VOLATILE HACE QUE SE EJECUTE DONDE ESTE DISPONIBLE
        // TAREAS ASINCRONAS


        // CONTEXTO DONDE ESTAMOS EJECUTANDO LOS PROCESOS
        // MUCHAS FORMAS DE EJECUTAR EL CONTEXTO
        fun getDatabase(context: Context): UserDataBase {
            val tempInntance = INSTANCE
            // ES DISTINTO A NULL
            if (tempInntance != null) {

                return tempInntance
            }

            // LLAMA A UN METODO Y LO SINCRONIZA PARA INSTANCIAR
            synchronized(this) {
                // CLASE DE ROOM ES EL CREADOR DE LA INSTANCIA DE LA BASE DE DATOS
                val instance = Room.databaseBuilder(
                    // la bade datos sea una para toda la app
                    context.applicationContext,
                    // NOMBRE DEL ARCHIVO QUE CONTIENE LA BASE DE DATO
                    UserDataBase::class.java,
                    "UserDatabase"
                )
                    .build()
                INSTANCE = instance
                return instance

            }
        }
    }
}