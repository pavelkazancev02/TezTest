package com.pavelkazancev02.teztest.data.room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SubscribedAccount::class], version = 6,  exportSchema = false)
abstract class SubscriptionsDatabase : RoomDatabase() {
    abstract val subscriptionsDatabaseDao: SubscriptionsDatabaseDao
    companion object {
        @Volatile
        private var INSTANCE: SubscriptionsDatabase? = null
        fun getInstance(context: Context): SubscriptionsDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriptionsDatabase::class.java,
                        "subscriptions_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}