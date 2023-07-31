package com.example.ejercicio1m6.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ejercicio1m6.Modelo.Model.User
import com.example.ejercicio1m6.Modelo.Model.UserDataBase
import com.example.ejercicio1m6.Modelo.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    val allUser: LiveData<List<User>>

    init {
        // obteniendo instancia del dao
        val userDao = UserDataBase.getDatabase(application).getUserDao()
        repository = UserRepository(userDao)
        allUser = repository.listAllUser
    }

    fun insertUser(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        repository.updateUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        repository.deleteUser(user)
    }

    fun userExists(user: String): Boolean {
        val userList = allUser.value
        return userList?.any { it.User == user } ?: false
    }
}

