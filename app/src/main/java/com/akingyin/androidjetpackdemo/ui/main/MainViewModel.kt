package com.akingyin.androidjetpackdemo.ui.main

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.akingyin.androidjetpackdemo.App
import com.akingyin.androidjetpackdemo.data.AppDatabase
import com.akingyin.androidjetpackdemo.entity.User
import com.akingyin.androidjetpackdemo.ui.adapter.UserListAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import java.util.*




class MainViewModel(application: Application) : AndroidViewModel(application) ,View.OnClickListener ,BaseQuickAdapter.OnItemClickListener
 ,BaseQuickAdapter.OnItemLongClickListener{
    // TODO: Implement the ViewModel
    var  user:User = User()

    var  adapter:UserListAdapter? = null
     val mUsers = MutableLiveData<User>()

    var users:MutableList<User> = mutableListOf()
     init {
         user.age = 1
         user.createDay = Date()
         user.name="test"
         mUsers.value = null

     }

    override fun onClick(p0: View?) {
        println("onClick")
        var  random:Random = Random()
        user.name = random.nextInt(100).toString()+"name"
        var  user2 = User()
        user2.name = user.name
        user2.createDay = Date()
        users.add(0,user2)
        println("adapter= ${adapter?.itemCount}")
        adapter?.notifyDataSetChanged()
        var  appDatabase =  AppDatabase.getInstance(getApplication(), App.INSTANCE?.appExecutors!!)
        App.INSTANCE!!.appExecutors.mDiskIO!!.execute {
            appDatabase?.userDao()?.saveUser(user2)
        }

    }

    override fun onItemClick(adapter2: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        println("itemClick=${ adapter?.getItem(position)?.name}")
    }

    override fun onItemLongClick(adapter2: BaseQuickAdapter<*, *>?, view: View?, position: Int): Boolean {
        adapter2?.remove(position)
       return true
    }


}
