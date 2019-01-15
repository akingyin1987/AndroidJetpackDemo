package com.akingyin.androidjetpackdemo

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.akingyin.androidjetpackdemo.data.AppDatabase.Companion.getInstance
import com.akingyin.androidjetpackdemo.data.AppExecutors



/**
 * @ Description:
 * @author king
 * @ Date 2019/1/14 16:33
 * @version V1.0
 */
class App  : Application(){

    lateinit var appExecutors :AppExecutors

    companion object {
        /*单例*/
        @Volatile
         var INSTANCE: App ? = null



    }
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appExecutors = AppExecutors()
        getInstance(this,appExecutors)
        showDebugDBAddressLogToast(this)
    }

    fun showDebugDBAddressLogToast(context: Context) {
        if (BuildConfig.DEBUG) {
            try {
                val debugDB = Class.forName("com.amitshekhar.DebugDB")
                val getAddressLog = debugDB.getMethod("getAddressLog")
                val value = getAddressLog.invoke(null)
                Toast.makeText(context, value as String, Toast.LENGTH_LONG).show()
            } catch (ignore: Exception) {

            }

        }
    }
}