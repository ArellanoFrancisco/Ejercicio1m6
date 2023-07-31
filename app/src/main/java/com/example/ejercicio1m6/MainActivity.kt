package com.example.ejercicio1m6



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicio1m6.Modelo.Model.OnItemClickListener
import com.example.ejercicio1m6.Modelo.Model.User
import com.example.ejercicio1m6.ViewModel.UserViewModel
import com.example.ejercicio1m6.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    val adapter = Adapter(this)
    val viewModel: UserViewModel by viewModels()
    var selectedUser: User? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // implementacion del Recycler View

        binding.RView.adapter = adapter
        binding.RView.layoutManager = LinearLayoutManager(this)
        binding.RView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        viewModel.allUser.observe(this) { userList ->
            adapter.updater(userList)
            val totalUsers = userList.size
            val userAddTotalString = resources.getString(R.string.UserAddTotal, totalUsers)
            binding.textTotalUser.text = userAddTotalString
        }


        binding.buttonAdd.setOnClickListener {
            val user = binding.TextUserName.text.toString()
            val username = binding.TextName.text.toString()
            val userAge1 = binding.TextAge.text.toString()
            if(user.isEmpty()||username.isEmpty()||userAge1.isEmpty()){
                Toast.makeText(this, "Debe agregar todos los Datos", Toast.LENGTH_SHORT).show()
            }else if (viewModel.userExists(user)) {
                val userAge=userAge1.toInt()
                Toast.makeText(this, "El usuario ya existe, datos Actualizados", Toast.LENGTH_SHORT).show()
                val newUser = User(User = user, UserName = username, UserAge = userAge)
                viewModel.updateUser(newUser)

                binding.TextUserName.text.clear()
                binding.TextName.text.clear()
                binding.TextAge.text.clear()

            } else {
                val userAge=userAge1.toInt()
                val newUser = User(User = user, UserName = username, UserAge = userAge)
                viewModel.insertUser(newUser)
                Toast.makeText(this, "Usuario agregado", Toast.LENGTH_SHORT).show()

                binding.TextUserName.text.clear()
                binding.TextName.text.clear()
                binding.TextAge.text.clear()
            }

        }

        binding.buttonDelete.setOnClickListener {
            if (selectedUser != null) {
                showConfirmationDialog()
            } else {
                Toast.makeText(this, "Seleccione al usuario", Toast.LENGTH_SHORT).show()
            }
        }

            /* val user = binding.TextUserName.text.toString()
         viewModel.deleteUser(user)
         Toast.makeText(this, "Usuario Eliminado", Toast.LENGTH_SHORT).show()

         binding.TextUserName.text.clear()
         binding.TextName.text.clear()
         binding.TextAge.text.clear()*/

        binding.buttonShow.setOnClickListener {
            adapter.notifyDataSetChanged()

        }

    }
    private fun showConfirmationDialog() {
        selectedUser?.let {
            val dialog = ConfirmDeleteDialogFragment(it, it.User)
            dialog.show(supportFragmentManager, "ConfirmDeleteDialog")
        } ?: run {
            Toast.makeText(this, "Seleccione un usuario", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(user: User, position: Int) {
        selectedUser = user
        adapter.setSelectedPosition(position)
    }
}