package com.akingyin.androidjetpackdemo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akingyin.androidjetpackdemo.entity.User

/**
 * @ Description:
 * @author king
 * @ Date 2019/1/15 15:54
 * @version V1.0
 */

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun   saveUser(user: User)

    @Query("Select * from tb_user  ")
    fun   findAllUser():LiveData<List<User>>

}