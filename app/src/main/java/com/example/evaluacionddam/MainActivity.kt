package com.example.evaluacionddam

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
        setContentView(R.layout.itemlista)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    iniciar()



    }




    private fun limpiar(){
        binding.etNombreproducto.setText("")
        binding.etPrecio.setText("")
        binding.etID.setText("")

        binding.etID.requestFocus()




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
                rvProductos.adapter = ProductoAdacter(listaProd, onClickListener = {
                    Toast.makeText(this@MainActivity, it.nombre, Toast.LENGTH_SHORT).show()
                })

                limpiar()
            }


        } catch (e: Exception) {
            e.printStackTrace()

            Toast.makeText(this, "Error al agregar, porfavor revisar los tipos de datos", Toast.LENGTH_SHORT).show()


        }

        limpiar()
    }




    private fun iniciar() {


        binding.btnGuardar.setOnClickListener {
            agregar()
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
                val id:Int=etbyId.text.toString().toInt()
                var encontrado=false
                var i=0


                while (i<listaProd.size && !encontrado){


                    if(listaProd[i].id==id){



                        listaProd[i].id=etID.text.toString().toInt()
                        listaProd[i].nombre=etNombreproducto.text.toString()
                        listaProd[i].precio=etPrecio.text.toString().toDouble()
                        encontrado=true

                    }
                    i++
                }
                if(encontrado){

                    rvProductos.layoutManager=LinearLayoutManager(this@MainActivity)
                    rvProductos.adapter=ProductoAdacter(listaProd, onClickListener = {

                    })

                }

    limpiar()
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
              val id:Int=etbyId.text.toString().toInt()

              var i=0
              var encontrado=false

              while (i<listaProd.size && !encontrado){


                  if(listaProd[i].id==id){
                       etID.setText(listaProd[i].id.toString())
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

