package com.example.ejercicio1m6

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio1m6.Modelo.Model.OnItemClickListener
import com.example.ejercicio1m6.Modelo.Model.User
import com.example.ejercicio1m6.databinding.ItemLayoutBinding



class Adapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<Adapter.RVViewHolder>() {

    private var listUser = listOf<User>()
    private var selectedPosition = RecyclerView.NO_POSITION


    fun updater (ListUser: List<User>){
        listUser = ListUser
    }

    fun setSelectedPosition(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.RVViewHolder {
        return  RVViewHolder( ItemLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: Adapter.RVViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user, position == selectedPosition)
    }

    override fun getItemCount(): Int = listUser.size


    inner class RVViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val user = listUser[bindingAdapterPosition]
                listener.onItemClick(user, bindingAdapterPosition)
            }
        }

    fun bind(user: User, isSelected: Boolean){
        binding.userAdd.text =  binding.root.context.getString(R.string.UserAdd, user.User)
        binding.userNameAdd.text = binding.root.context.getString(R.string.UserNameAdd, user.UserName)
        binding.userAgeAdd.text = binding.root.context.getString(R.string.UserAgeAdd, user.UserAge)

        if (isSelected) {
            binding.root.setBackgroundColor(Color.GRAY)
        } else {
            binding.root.setBackgroundColor(Color.TRANSPARENT)
        }

}
}
}