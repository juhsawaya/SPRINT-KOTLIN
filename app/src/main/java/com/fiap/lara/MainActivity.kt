package com.fiap.lara

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fiap.lara.navigation.LaraNavHost
import com.fiap.lara.ui.theme.LaraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaraTheme {
                LaraNavHost()
            }
        }
    }
}
