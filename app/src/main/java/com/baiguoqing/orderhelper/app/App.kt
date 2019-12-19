package com.baiguoqing.orderhelper.app

import android.app.Application
import com.baiguoqing.orderhelper.db.DataBaseManager

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var dbManager: DataBaseManager

    override fun onCreate() {
        super.onCreate()
        instance = this
        dbManager = DataBaseManager()
    }

}