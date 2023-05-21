package com.example.recyclerviewproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AppDataClass(var appName : String,var appReleaseDate : String,var appCategory: String,var appIcon: Int,var appStoreUrl: String ): Parcelable
