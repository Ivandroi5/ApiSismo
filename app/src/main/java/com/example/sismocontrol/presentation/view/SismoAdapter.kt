package com.example.sismocontrol.presentation.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sismocontrol.data.model.Sismo
import com.example.sismocontrol.databinding.SismoItemBinding


class SismoAdapter: RecyclerView.Adapter<SismoAdapter.SismoViewHolder>() {

    lateinit var onItemClickListener: (Sismo) -> Unit

    // Atributo de la clase
    var sismos = mutableListOf<Sismo>()
        @SuppressLint("NotifyDataSetChanged")
        set(value){
            field = value
            this.notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SismoViewHolder {

        val bindingItem =
            SismoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SismoViewHolder(bindingItem)

    }

    override fun onBindViewHolder(holder: SismoViewHolder, position: Int) {
            val sismo: Sismo = sismos[position]
            holder.bind(sismo)
    }

    override fun getItemCount(): Int {
            return sismos.size
    }

    inner class SismoViewHolder(private var bindingItem: SismoItemBinding)
        : RecyclerView.ViewHolder(bindingItem.root) {
             fun bind(sismo: Sismo){
                 with(sismo){
                     bindingItem.magnitudTxt.text = String.format("%.2f", magnitud)
                     bindingItem.locacionTxt.text = "["+lugar+"]"
                 }

                 bindingItem.root.setOnClickListener {
                     if(::onItemClickListener.isInitialized)
                         onItemClickListener(sismo)
                     else
                         Log.e("Adapter", "Listener not initialized")
                 }
             }
        }
}