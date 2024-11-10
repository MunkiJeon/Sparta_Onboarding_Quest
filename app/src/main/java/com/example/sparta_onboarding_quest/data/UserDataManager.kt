package com.example.sparta_onboarding_quest.data

import android.content.Context
import android.content.SharedPreferences

class UserDataManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("User_DATA", Context.MODE_PRIVATE)

    fun saveUserData(
        userId: String,
        password: String,
        name: String,
        birthDate: String,
        mbti: String,
        gender: String
    ) {
        with(sharedPreferences.edit()) {
            putString("user_id", userId)
            putString("password", password)
            putString("name", name)
            putString("birth_date", birthDate)
            putString("mbti", mbti)
            putString("gender", gender)
            apply() // 변경사항 저장
        }
    }

    // 사용자 데이터를 가져오는 함수
    fun getUserData(userId: String): Map<String, String?> {
        return mapOf(
            "user_id" to userId,
            "password" to sharedPreferences.getString("${userId}_password", null),
            "name" to sharedPreferences.getString("${userId}_name", null),
            "birth_date" to sharedPreferences.getString("${userId}_birth_date", null),
            "mbti" to sharedPreferences.getString("${userId}_mbti", null),
            "gender" to sharedPreferences.getString("${userId}_gender", null)
        )
    }
}