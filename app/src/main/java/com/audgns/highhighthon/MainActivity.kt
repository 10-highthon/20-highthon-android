package com.audgns.highhighthon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.audgns.highhighthon.network.RetrofitClient
import com.audgns.highhighthon.ui.HighthonApp
import com.audgns.highhighthon.ui.theme.HighhighthonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        RetrofitClient.init(this)
        setContent {
            HighhighthonTheme {
                HighthonApp()
            }
        }
    }
}