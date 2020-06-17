package com.pavelkazancev02.teztest.data.room_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "subscribed_accounts_table")
data class SubscribedAccount(
    @PrimaryKey(autoGenerate = true)
    val accountId: Long = 0L,
    @ColumnInfo(name = "account_address")
    val accountAddress: String = "account_address",
    @ColumnInfo(name = "network_type")
    val networkType: String = "Mainnet"
)