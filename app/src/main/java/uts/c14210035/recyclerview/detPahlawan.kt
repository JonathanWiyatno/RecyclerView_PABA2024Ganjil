package uts.c14210035.recyclerview

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.squareup.picasso.Picasso

class detPahlawan : AppCompatActivity() {



    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.detail_pahlawan)

        var _ivFoto = findViewById<ImageView>(R.id.ivFoto)
        var _tvNama = findViewById<TextView>(R.id.tvNama)
        var _tvDetail = findViewById<TextView>(R.id.tvDetail)


        val dataIntent = intent.getParcelableExtra<wayang>("kirimData", wayang::class.java)

        if (dataIntent!=null){
            Picasso.get()
                .load(dataIntent.foto)
                .into(_ivFoto)
            _tvNama.setText(dataIntent.nama)
            _tvDetail.setText(dataIntent.deskripsi)



        }


    }


}