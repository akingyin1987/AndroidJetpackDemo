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

    // 创建LiveData
     val mutableLiveData = MutableLiveData<MutableList<User>>()

     var postionLiveData = MutableLiveData<Int>()

     var userLiveData = MutableLiveData<User>()

     fun onAddUser(user: User){

        // mutableLiveData.postValue(users)

         var  appDatabase =  AppDatabase.getInstance(getApplication(), App.INSTANCE?.appExecutors!!)
         App.INSTANCE!!.appExecutors.mDiskIO!!.execute {
             appDatabase?.userDao()?.saveUser(user)
             userLiveData.postValue(user)
         }
     }

    fun onDelect(user: User?,position: Int){

       // mutableLiveData.postValue(users)
        var  appDatabase =  AppDatabase.getInstance(getApplication(), App.INSTANCE?.appExecutors!!)
        App.INSTANCE!!.appExecutors.mDiskIO!!.execute {
            if (user != null) {
                appDatabase?.userDao()?.delectUser(user)
                postionLiveData.postValue(position)
            }
        }
    }

    var  user:User = User()

    var  adapter:UserListAdapter? = null
     val mUsers = MutableLiveData<User>()

    var users:MutableList<User> = mutableListOf()
     init {
         user.age = 1
         user.createDay = Date()
         user.name="test"
         mUsers.value = null
         var  appDatabase =  AppDatabase.getInstance(getApplication(), App.INSTANCE?.appExecutors!!)
         App.INSTANCE!!.appExecutors.mDiskIO!!.execute {
             users = appDatabase?.userDao()?.findUsers()!!
             mutableLiveData.postValue(users)
         }
     }

    override fun onClick(p0: View?) {

        var  random = Random()
        user.name = random.nextInt(100).toString()+"name"
        var  user2 = User()
        user2.name = user.name
        user2.createDay = Date()

        println("adapter= ${adapter?.itemCount}")
        onAddUser(user2)


    }

    override fun onItemClick(adapter2: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        println("itemClick=${ adapter?.getItem(position)?.name}")
    }

    override fun onItemLongClick(adapter2: BaseQuickAdapter<*, *>?, view: View?, position: Int): Boolean {

        var  user = adapter?.getItem(position)

        onDelect(user,position)
       return true
    }


}
