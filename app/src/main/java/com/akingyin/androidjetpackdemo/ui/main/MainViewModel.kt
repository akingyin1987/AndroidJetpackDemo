package com.akingyin.androidjetpackdemo.ui.main

import android.view.View
import androidx.lifecycle.ViewModel
import com.akingyin.androidjetpackdemo.entity.User
import com.blankj.utilcode.util.TimeUtils
import java.util.*


class MainViewModel : ViewModel() ,View.OnClickListener {
    // TODO: Implement the ViewModel
    var  user:User = User()

     init {
         user.age = 1
         user.createDay = Date()
         user.name="test"
     }

    override fun onClick(p0: View?) {
        println("onClick")
        var  random:Random = Random()
        user.name = random.nextInt(100).toString()+"name"

    }
}
