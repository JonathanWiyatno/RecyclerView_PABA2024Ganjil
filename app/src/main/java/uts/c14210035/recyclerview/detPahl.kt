package uts.c14210035.recyclerview

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.squareup.picasso.Picasso
import uts.c14210035.recyclerview.ui.theme.RecyclerViewTheme

class detPahl : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



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


