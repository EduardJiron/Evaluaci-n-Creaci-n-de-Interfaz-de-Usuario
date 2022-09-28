package com.example.evaluacionddam.dataadpter

import android.app.Person
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluacionddam.R
import com.example.evaluacionddam.databinding.ItemlistaBinding
import com.example.evaluacionddam.dataclass.Producto

class ProductoAdacter(var listProd:MutableList<Producto>):RecyclerView.Adapter<ProductoAdacter.ProductoHolder>() {

inner class ProductoHolder(private val binding:ItemlistaBinding):RecyclerView.ViewHolder(binding.root){



    fun cargar(producto:Producto){

        with(binding){
            tvCodProd.text=producto.id.toString()
            tvNombreProd.text=producto.nombre
            tvPrecioProd.text=producto.precio.toString()

        }
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoHolder {

        val binding=ItemlistaBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ProductoHolder(binding)



    }

    override fun onBindViewHolder(holder: ProductoHolder, position: Int) {


        holder.cargar(listProd[position])

    }

    override fun getItemCount(): Int =listProd.size

}






