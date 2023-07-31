package com.example.ejercicio1m6

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio1m6.Modelo.Model.User
import com.example.ejercicio1m6.databinding.DialogConfirmDeleteBinding

class ConfirmDeleteDialogFragment(private val user: User, private val userName: String) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bindingd = DialogConfirmDeleteBinding.inflate(requireActivity().layoutInflater)
        bindingd.UserChoose.text = resources.getString(R.string.Userdelete, userName)

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(bindingd.root)

        bindingd.buttonAceptar.setOnClickListener {
            (requireActivity() as MainActivity).viewModel.deleteUser(user)
            (requireActivity() as MainActivity).selectedUser = null // Limpiar la selección aquí
            (requireActivity() as MainActivity).adapter.setSelectedPosition(RecyclerView.NO_POSITION)
            Toast.makeText(requireActivity()as MainActivity, "Usuario Eliminado", Toast.LENGTH_SHORT).show()
            dismiss() // Cerrar el diálogo después de hacer clic en "Aceptar"
        }

        bindingd.buttonCancelar.setOnClickListener {
            dismiss() // Cerrar el diálogo cuando se haga clic en "Cancelar"
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}