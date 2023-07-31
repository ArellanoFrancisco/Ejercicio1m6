package com.example.ejercicio1m6.Modelo

import androidx.lifecycle.LiveData
import com.example.ejercicio1m6.Modelo.Model.User
import com.example.ejercicio1m6.Modelo.Model.UserDao

class UserRepository(private val userDao: UserDao) {



    val listAllUser: LiveData<List<User>> = userDao.getAllUser()


    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

}