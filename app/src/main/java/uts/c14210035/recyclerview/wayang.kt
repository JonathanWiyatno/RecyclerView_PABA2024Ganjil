package uts.c14210035.recyclerview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class wayang(
    var id: Int,
    var foto: String,
    var nama: String,
    var karakter: String,
    var deskripsi: String,
    var isSaved: Boolean = false
):Parcelable
