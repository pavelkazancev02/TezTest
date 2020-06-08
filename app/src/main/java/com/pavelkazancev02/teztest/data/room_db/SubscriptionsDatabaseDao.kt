package com.pavelkazancev02.teztest.data.room_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SubscriptionsDatabaseDao {
    @Insert
    fun insert(account: SubscribedAccount)
    @Update
    fun update(account: SubscribedAccount)
    @Query ("SELECT * from subscribed_accounts_table WHERE accountId = :key")
    fun getByKey(key: Long): SubscribedAccount?
    @Query ("SELECT * from subscribed_accounts_table WHERE account_address = :address")
    fun getByAddress(address: String): SubscribedAccount?
    @Query ("DELETE from subscribed_accounts_table WHERE account_address = :address")
    fun removeByAddress(address: String)
    @Query("DELETE FROM subscribed_accounts_table")
    fun clear()
    @Query("SELECT * FROM subscribed_accounts_table ORDER BY accountId DESC")
    fun getAllAccounts(): LiveData<List<SubscribedAccount>>
    @Query("SELECT * FROM subscribed_accounts_table ORDER BY accountId DESC LIMIT 1")
    fun getLastAccount(): SubscribedAccount?

}