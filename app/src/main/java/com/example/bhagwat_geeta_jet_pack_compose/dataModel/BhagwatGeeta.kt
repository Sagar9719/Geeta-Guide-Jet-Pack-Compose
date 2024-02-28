package com.example.bhagwat_geeta_jet_pack_compose.dataModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BhagwatGeeta (
    val id: Int,
    val name: String,
    val slug: String,
    val name_transliterated: String,
    val  name_translated: String,
    val verses_count: Int,
    val chapter_number: Int,
    val name_meaning: String,
    val chapter_summary: String,
    val chapter_summary_hindi: String
) : Parcelable