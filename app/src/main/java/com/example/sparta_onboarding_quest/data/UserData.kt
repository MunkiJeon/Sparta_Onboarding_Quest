package com.example.sparta_onboarding_quest.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
    var id: String,
    var pw: String,
    var name: String,
    var age: String = "20",
    var mbti: String = "ESTJ",
    var gender: String = "남성"
) : Parcelable