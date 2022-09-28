package com.example.evaluacionddam

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evaluacionddam.dataadpter.ProductoAdacter
import com.example.evaluacionddam.databinding.ActivityMainBinding
import com.example.evaluacionddam.dataclass.Producto


class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding
   var listaProd=ArrayList<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniciar()


    }

    private fun limpiar(){
        with(binding){

            etID.setText("")
            etNombreproducto.setText("")
            etPrecio.setText("")
            etID.requestFocus()
        }
    }

    private fun agregar() {

        try {
            with(binding) {

                val id: Int = etID.text.toString().toInt()
                val nombre: String = etNombreproducto.text.toString()
                val precio: Double = etPrecio.text.toString().toDouble()
                val prod = Producto(id, nombre, precio)


                listaProd.add(prod)

                rvProductos.layoutManager = LinearLayoutManager(this@MainActivity)
                rvProductos.adapter = ProductoAdacter(listaProd)

            }


        } catch (e: Exception) {
            e.printStackTrace()

            Toast.makeText(this, "Error al agregar, porfavor revisar los tipos de datos", Toast.LENGTH_SHORT).show()


        }

        limpiar()
    }

    private fun borrar(){

        try {

            with(binding){


                val id:Int=etID.text.toString().toInt()
                var i=0
                var encontrado=false
                while (i<listaProd.size && !encontrado){
                    if(listaProd[i].id==id){
                        listaProd.removeAt(i)
                        encontrado=true
                    }
                    i++
                }
                if(encontrado){
                    rvProductos.layoutManager=LinearLayoutManager(this@MainActivity)
                    rvProductos.adapter=ProductoAdacter(listaProd)
                }
            }


        }
        catch (e:Exception){
            Toast.makeText(this@MainActivity,"Inserte el id correcto para eliminar" ,Toast.LENGTH_SHORT).show()
        }
        limpiar()
    }



    private fun iniciar() {


        binding.btnGuardar.setOnClickListener {
            agregar()
        }

        binding.btnBorrar.setOnClickListener {

                AlertDialog.Builder(this)
                    .setTitle("Borrar")
                    .setMessage("¿Estas seguro de borrar?")
                    .setPositiveButton("Si") { _, _ ->
                        borrar()

                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                        Toast.makeText(this, "No se borro", Toast.LENGTH_SHORT).show()
                    }
                    .show()


        }
        binding.btneditar.setOnClickListener {

            editar()

        }
        binding.btnBuscar.setOnClickListener {

            buscar()

        }
    }
    private fun editar(){

        try {
            with(binding){
                val id:Int=etID.text.toString().toInt()
                var encontrado=false
                var i=0


                while (i<listaProd.size && !encontrado){


                    if(listaProd[i].id==id){




                        listaProd[i].nombre=etNombreproducto.text.toString()
                        listaProd[i].precio=etPrecio.text.toString().toDouble()
                        encontrado=true

                    }
                    i++
                }
                if(encontrado){

                    rvProductos.layoutManager=LinearLayoutManager(this@MainActivity)
                    rvProductos.adapter=ProductoAdacter(listaProd)

                }


            }
        }
        catch (e:Exception){

            Toast.makeText(this@MainActivity,"Por favor inserte un id correcto para editar",Toast.LENGTH_SHORT).show()
        }
        limpiar()
}
    private fun buscar(){

        try{

            with(binding){
              val id:Int=etID.text.toString().toInt()

              var i=0
              var encontrado=false

              while (i<listaProd.size && !encontrado){


                  if(listaProd[i].id==id){

                      etNombreproducto.setText(listaProd[i].nombre)
                      etPrecio.setText(listaProd[i].precio.toString())


                      encontrado=true

                  }


                  i++
              }


          }


      }
      catch (e:Exception){
          Toast.makeText(this, "Ingrese un id correcto para buscar el producto",Toast.LENGTH_SHORT).show()
      }
    }

}
