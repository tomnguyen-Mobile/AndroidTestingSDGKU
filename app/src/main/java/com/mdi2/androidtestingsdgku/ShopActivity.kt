package com.mdi2.androidtestingsdgku

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class ShopActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent{
            ShopScreen()
        }
    }

    @Composable
    fun ShopScreen(){
        Text("Welcome to the shop!")
    }

    @Preview
    @Composable
    fun ShopScreenPreview(){
        ShopScreen()
    }
}

