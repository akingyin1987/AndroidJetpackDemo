package com.akingyin.androidjetpackdemo.data

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors


/**
 * @ Description:
 * @author king
 * @ Date 2019/1/14 18:05
 * @version V1.0
 */
class  AppExecutors (){

      var mDiskIO: Executor? = null

      var mNetworkIO: Executor? = null

      var mMainThread: Executor? = null



    init {
        mDiskIO = Executors.newSingleThreadExecutor()
        mNetworkIO = Executors.newFixedThreadPool(3)
        mMainThread = MainThreadExecutor()
    }

    constructor(mDiskIO:Executor, mNetworkIO: Executor, mMainThread: Executor):this(){
        this.mDiskIO = mDiskIO
        this.mNetworkIO = mNetworkIO
        this.mMainThread = mMainThread
    }




    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}