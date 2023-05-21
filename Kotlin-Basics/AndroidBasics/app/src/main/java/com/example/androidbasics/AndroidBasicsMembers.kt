package com.example.androidbasics


data class AndroidBasicsMembers (
    val fullName: String,
    var title: String,
    val horoscope: String,
    val memberLevel: String,
    val homeTown: String,
    var age: Int,
    val contact: ContactInformation,
    val team: EnumType,
    var motivationLevel: Int? = null

)
