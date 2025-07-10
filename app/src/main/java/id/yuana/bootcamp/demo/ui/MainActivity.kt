package id.yuana.bootcamp.demo.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import id.yuana.bootcamp.demo.R

class MainActivity : AppCompatActivity(R.layout.activity_main)

sealed class UiEffect {
    object PopBackStack : UiEffect()
    data class Navigate(val directions: NavDirections) : UiEffect()
    data class ShowToast(val message: String) : UiEffect()
}