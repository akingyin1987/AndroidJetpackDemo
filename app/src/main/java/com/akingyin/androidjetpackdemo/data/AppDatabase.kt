package com.akingyin.androidjetpackdemo.data

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.akingyin.androidjetpackdemo.data.db.converter.DateConverter
import com.akingyin.androidjetpackdemo.data.db.dao.UserDao
import com.akingyin.androidjetpackdemo.entity.User









/**
 * @ Description:
 * @author king
 * @ Date 2019/1/14 17:53
 * @version V1.0
 */

@Database(entities = arrayOf(User::class),version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase :RoomDatabase(){

    abstract fun userDao(): UserDao
    companion object{
        var sInstance: AppDatabase? = null

        @VisibleForTesting
        val DATABASE_NAME = "basic-sample-db"

        private val mIsDatabaseCreated = MutableLiveData<Boolean>()

        fun getInstance(context: Context, executors: AppExecutors): AppDatabase? {
            if (sInstance == null) {
                synchronized(AppDatabase::class.java) {
                    if (sInstance == null) {
                        sInstance = buildDatabase(context.getApplicationContext(), executors)
                        sInstance?.updateDatabaseCreated(context.getApplicationContext())
                    }
                }
            }
            return sInstance
        }
        fun buildDatabase(appContext: Context,
                          executors: AppExecutors): AppDatabase {
            return Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            executors.mDiskIO?.execute({
                                // Add a delay to simulate a long-running operation
                                addDelay()
                                // Generate the data for pre-population
                                val database = getInstance(appContext, executors)
//                            val products = DataGenerator.generateProducts()
//                            val comments = DataGenerator.generateCommentsForProducts(products)
//
//                            insertData(database, products)
                                // notify that the database was created and it's ready to be used
                                database?.setDatabaseCreated()
                            })
                        }
                    })
               //     .addMigrations(MIGRATION_1_2)
                    .build()
        }
        private fun addDelay() {
            try {
                Thread.sleep(4000)
            } catch (ignored: InterruptedException) {
            }

        }
    }






    /**
     * Check whether the database already exists and expose it via [.getDatabaseCreated]
     */
    private fun updateDatabaseCreated(context: Context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated()
        }
    }

    private fun setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true)
    }

    private fun insertData(database: AppDatabase, products: List<User>) {
        database.runInTransaction {

        }
    }

    fun getDatabaseCreated(): LiveData<Boolean> {

        return mIsDatabaseCreated
    }

    private val MIGRATION_1_2 = object : Migration(1, 2) {

        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `productsFts` USING FTS4(" + "`name` TEXT, `description` TEXT, content=`products`)")
            database.execSQL("INSERT INTO productsFts (`rowid`, `name`, `description`) " + "SELECT `id`, `name`, `description` FROM products")

        }
    }

}