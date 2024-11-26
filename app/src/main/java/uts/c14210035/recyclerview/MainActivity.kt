package uts.c14210035.recyclerview

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uts.c14210035.recyclerview.ui.theme.RecyclerViewTheme

class MainActivity : ComponentActivity() {

    private var _nama : MutableList<String> = emptyList<String>().toMutableList()
    private var _karakter : MutableList<String> = emptyList<String>().toMutableList()
    private var _deskripsi : MutableList<String> = emptyList<String>().toMutableList()
    private var  _gambar : MutableList<String> = emptyList<String>().toMutableList()

    private var arWayang = arrayListOf<wayang>()

    lateinit var sp : SharedPreferences

    private lateinit var _rvWayang : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        sp = getSharedPreferences("dataSP", MODE_PRIVATE)

        val gson =  Gson()
        val isiSP = sp.getString("spWayang", null)
        val type = object : TypeToken<ArrayList<wayang>> () {}.type

        if (isiSP!=null) {
            arWayang = gson.fromJson(isiSP, type)
        }

        _rvWayang = findViewById<RecyclerView>(R.id.rvWayang)
        if (arWayang.size == 0) {
            SiapkanData()
        }
        else {
            arWayang.forEach {
                _nama.add(it.nama)
                _gambar.add(it.foto)
                _deskripsi.add(it.foto)
                _deskripsi.add(it.deskripsi)
                _karakter.add(it.karakter)
            }

            arWayang.clear()
        }

        TambahData()
        TampilkanData()



    }

    fun SiapkanData(){
        _nama = resources.getStringArray(R.array.namaWayang).toMutableList()
        _deskripsi = resources.getStringArray(R.array.deskripsiWayang).toMutableList()
        _karakter = resources.getStringArray(R.array.karakterUtamaWayang).toMutableList()
        _gambar = resources.getStringArray(R.array.gambarWayang).toMutableList()
    }

    fun TambahData(){
        val gson = Gson()
        val editor = sp.edit()
        arWayang.clear()
        for (position in _nama.indices){
            val data = wayang(
                _gambar[position],
                _nama[position],
                _karakter[position],
                _deskripsi[position]
            )

            arWayang.add(data)

        }

        val json = gson.toJson(arWayang)
        editor.putString("spWayang", json)
        editor.apply()

    }

    fun TampilkanData(){
        _rvWayang.layoutManager = LinearLayoutManager(this)
        _rvWayang.adapter = adapterRecView(arWayang)
        val adapterWayang = adapterRecView(arWayang)
        _rvWayang.adapter = adapterWayang

        adapterWayang.setOnItemClickCallback(object : adapterRecView.OnItemClickCallback{
            override fun onItemClicked(data: wayang) {
                Toast.makeText(this@MainActivity, data.nama, Toast.LENGTH_LONG).show()

                val intent = Intent(this@MainActivity,detPahl::class.java )
                intent.putExtra("kirimData",data)
                startActivity(intent)

            }

            override fun delData(pos: Int){
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("HAPUS DATA")
                    .setMessage("Apakah Benar Data "+_nama[pos]+" akan dihapus? ")
                    .setPositiveButton(
                        "HAPUS",
                        DialogInterface.OnClickListener{dialog, which ->
                            _gambar.removeAt(pos)
                            _nama.removeAt(pos)
                            _deskripsi.removeAt(pos)
                            _karakter.removeAt(pos)
                            TambahData()
                            TampilkanData()
                        }
                    )
                    .setNegativeButton(
                        "BATAL",
                        DialogInterface.OnClickListener { dialog, which ->
                            Toast.makeText(
                                this@MainActivity,
                                "DATA BATAL DIHAPUS",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    ).show()
            }
        })

    }
}

