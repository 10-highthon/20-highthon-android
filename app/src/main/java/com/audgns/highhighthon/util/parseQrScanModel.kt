package com.audgns.highhighthon.util

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONObject
import java.util.UUID

data class QrScanModel(
    val id: UUID,
)

internal fun String.parseQrScanModel(): QrScanModel {
    val userId = UUID.fromString(this) // 문자열을 UUID로 변환
    return QrScanModel(userId)
}

fun saveQrScanModel(context: Context, qrScanModel: QrScanModel) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("id", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("id", qrScanModel.id.toString()) // UUID를 문자열로 저장
    editor.apply()
}

fun getQrScanUserId(context: Context): String? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("id", Context.MODE_PRIVATE)
    return sharedPreferences.getString("id", null)
}
