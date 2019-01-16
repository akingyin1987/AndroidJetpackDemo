package com.akingyin.androidjetpackdemo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
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

    @Delete
    fun   delectUser(user: User)

    @Query("Select * from tb_user ")
    fun   findAllUser():LiveData<List<User>>

    @Query("Select * from tb_user ")
    fun   findUsers():MutableList<User>

}