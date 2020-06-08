package com.pavelkazancev02.teztest.data.value_object.explorer_tip


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExplorerTip(
    @Json(name = "block_hash")
    val blockHash: String,
    @Json(name = "chain_id")
    val chainId: String,
    @Json(name = "cleared_accounts_30d")
    val clearedAccounts30d: Int,
    val cycle: Int,
    val delegates: Int,
    val delegators: Int,
    val deployments: List<Deployment>,
    @Json(name = "funded_accounts")
    val fundedAccounts: Int,
    @Json(name = "funded_accounts_30d")
    val fundedAccounts30d: Int,
    @Json(name = "genesis_time")
    val genesisTime: String,
    val health: Int,
    val height: Int,
    @Json(name = "inflation_1y")
    val inflation1y: Double,
    @Json(name = "inflation_rate_1y")
    val inflationRate1y: Double,
    val name: String,
    val network: String,
    @Json(name = "new_accounts_30d")
    val newAccounts30d: Int,
    @Json(name = "roll_owners")
    val rollOwners: Int,
    val rolls: Int,
    val status: Status,
    val supply: Supply,
    val symbol: String,
    val timestamp: String,
    @Json(name = "total_accounts")
    val totalAccounts: Int,
    @Json(name = "total_ops")
    val totalOps: Int
)