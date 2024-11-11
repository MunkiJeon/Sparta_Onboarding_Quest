package com.example.sparta_onboarding_quest.data

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONObject
import java.io.File

class UserDataManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("User_DATA", Context.MODE_PRIVATE)
    private val userDataFile = File(context.filesDir, "user_data.json")

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

        // 내부 저장소 JSON 파일에 저장
        val userJson = JSONObject().apply {
            put("user_id", userId)
            put("password", password)
            put("name", name)
            put("birth_date", birthDate)
            put("mbti", mbti)
            put("gender", gender)
        }

        // 기존 데이터를 가져와 추가
        val allUserData = getAllUserDataFromFile().apply {
            put(userId, userJson)
        }
        userDataFile.writeText(allUserData.toString())
    }


    // 사용자 데이터를 가져오는 함수
    fun getUserData(userId: String): Map<String, String?> {
        return mapOf(
            "user_id" to userId,
            "password" to sharedPreferences.getString("${userId}_password", null),
            "name" to sharedPreferences.getString("${userId}_name", null),
            "age" to sharedPreferences.getString("${userId}_age", null),
            "mbti" to sharedPreferences.getString("${userId}_mbti", null),
            "gender" to sharedPreferences.getString("${userId}_gender", null)
        )
    }

    // 내부 저장소 JSON 파일에서 전체 사용자 데이터를 가져오는 함수
    private fun getAllUserDataFromFile(): JSONObject {
        return if (userDataFile.exists()) {
            JSONObject(userDataFile.readText())
        } else {
            JSONObject()
        }
    }

    // userId를 받아 해당 ID로 저장된 사용자 데이터를 내부 저장소 JSON 파일에서 가져오는 함수
    fun getUserDataFromFile(userId: String): JSONObject? {
        val allUserData = getAllUserDataFromFile()
        return allUserData.optJSONObject(userId)
    }
}