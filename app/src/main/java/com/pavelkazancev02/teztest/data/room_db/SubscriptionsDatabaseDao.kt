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
    @Query("SELECT * FROM subscribed_accounts_table WHERE network_type = :networkType ORDER BY accountId DESC")
    fun getAllAccounts(networkType: String): LiveData<List<SubscribedAccount>>
    @Query("SELECT * FROM subscribed_accounts_table WHERE network_type = :networkType ORDER BY accountId DESC")
    fun getAllAccountsList(networkType: String): List<SubscribedAccount>?
    @Query("SELECT * FROM subscribed_accounts_table ORDER BY accountId DESC LIMIT 1")
    fun getLastAccount(): SubscribedAccount?
    @Query ("SELECT last_transaction from subscribed_accounts_table WHERE account_address = :address")
    fun getLastTransaction(address: String): String?
    @Query ("UPDATE subscribed_accounts_table SET last_transaction=:hash WHERE account_address = :address")
    fun updateLastTransaction(address: String, hash: String?)
    @Query ("SELECT new_transactions from subscribed_accounts_table WHERE account_address = :address")
    fun getNewTransactions(address: String): Int
    @Query ("UPDATE subscribed_accounts_table SET new_transactions=:count WHERE account_address = :address")
    fun updateNewTransactions(address: String, count: Int)

}