package id.yuana.bootcamp.demo

import android.app.Application
import id.yuana.bootcamp.demo.di.AppModule

class DemoApp : Application() {

    lateinit var appModule: AppModule

    override fun onCreate() {
        super.onCreate()

        appModule = AppModule
    }
}