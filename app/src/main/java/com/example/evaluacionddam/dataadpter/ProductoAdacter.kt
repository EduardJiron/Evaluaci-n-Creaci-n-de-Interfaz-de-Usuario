package com.example.evaluacionddam.dataadpter


import android.app.AlertDialog
import android.app.Person
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluacionddam.MainActivity
import com.example.evaluacionddam.R
import com.example.evaluacionddam.databinding.ActivityMainBinding
import com.example.evaluacionddam.databinding.ItemlistaBinding
import com.example.evaluacionddam.dataclass.Producto


class ProductoAdacter(var listProd:MutableList<Producto>,private val onClickListener:(Producto)->Unit ):RecyclerView.Adapter<ProductoAdacter.ProductoHolder>() {


    private lateinit var binding2: ActivityMainBinding



inner class ProductoHolder(private val binding:ItemlistaBinding):RecyclerView.ViewHolder(binding.root){



    fun cargar(producto:Producto){


        with(binding){
            tvCodProd.text=producto.id.toString()
            tvNombreProd.text=producto.nombre
            tvPrecioProd.text=producto.precio.toString()
            imgDelete.setOnClickListener {
                AlertDialog.Builder(binding.root.context)
                    .setTitle("Borrar")
                    .setMessage("¿Estas seguro de borrar?")
                    .setPositiveButton("Si") { _, _ ->
                        listProd.remove(producto)
                         listProd.remove(producto)
                        notifyDataSetChanged()

                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                        Toast.makeText(binding.root.context, "No se borro", Toast.LENGTH_SHORT).show()
                    }
                    .show()




            }






        }


    }


}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoAdacter.ProductoHolder {

        val binding=ItemlistaBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ProductoHolder(binding)



    }

    override fun onBindViewHolder(holder: ProductoAdacter.ProductoHolder, position: Int) {


        holder.cargar(listProd[position])

    }

    override fun getItemCount(): Int =listProd.size



    }




