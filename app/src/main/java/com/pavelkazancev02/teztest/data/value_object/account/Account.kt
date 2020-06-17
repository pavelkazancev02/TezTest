package com.pavelkazancev02.teztest.data.value_object.account


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Account(
    @Json(name = "active_delegations")
    val activeDelegations: Int,
    val address: String,
    @Json(name = "address_type")
    val addressType: String,
    @Json(name = "blocks_baked")
    val blocksBaked: Int,
    @Json(name = "blocks_endorsed")
    val blocksEndorsed: Int,
    @Json(name = "blocks_missed")
    val blocksMissed: Int,
    @Json(name = "blocks_stolen")
    val blocksStolen: Int,
    val `delegate`: String,
    @Json(name = "delegate_since")
    val delegateSince: Int,
    @Json(name = "delegate_since_time")
    val delegateSinceTime: String,
    @Json(name = "delegated_balance")
    val delegatedBalance: Double,
    @Json(name = "delegated_since")
    val delegatedSince: Int,
    @Json(name = "delegated_since_time")
    val delegatedSinceTime: String,
    @Json(name = "first_in")
    val firstIn: Int,
    @Json(name = "first_in_time")
    val firstInTime: String,
    @Json(name = "first_out")
    val firstOut: Int,
    @Json(name = "first_out_time")
    val firstOutTime: String,
    @Json(name = "first_seen")
    val firstSeen: Int,
    @Json(name = "first_seen_time")
    val firstSeenTime: String,
    @Json(name = "flow_rank")
    val flowRank: Int,
    @Json(name = "frozen_deposits")
    val frozenDeposits: Int,
    @Json(name = "frozen_fees")
    val frozenFees: Double,
    @Json(name = "frozen_rewards")
    val frozenRewards: Double,
    @Json(name = "grace_period")
    val gracePeriod: Int,
    @Json(name = "is_activated")
    val isActivated: Boolean,
    @Json(name = "is_active_delegate")
    val isActiveDelegate: Boolean,
    @Json(name = "is_contract")
    val isContract: Boolean,
    @Json(name = "is_delegatable")
    val isDelegatable: Boolean,
    @Json(name = "is_delegate")
    val isDelegate: Boolean,
    @Json(name = "is_delegated")
    val isDelegated: Boolean,
    @Json(name = "is_funded")
    val isFunded: Boolean,
    @Json(name = "is_revealed")
    val isRevealed: Boolean,
    @Json(name = "is_spendable")
    val isSpendable: Boolean,
    @Json(name = "is_vesting")
    val isVesting: Boolean,
    @Json(name = "last_bake_block")
    val lastBakeBlock: String,
    @Json(name = "last_bake_height")
    val lastBakeHeight: Int,
    @Json(name = "last_bake_time")
    val lastBakeTime: String,
    @Json(name = "last_endorse_block")
    val lastEndorseBlock: String,
    @Json(name = "last_endorse_height")
    val lastEndorseHeight: Int,
    @Json(name = "last_endorse_time")
    val lastEndorseTime: String,
    @Json(name = "last_in")
    val lastIn: Int,
    @Json(name = "last_in_time")
    val lastInTime: String,
    @Json(name = "last_out")
    val lastOut: Int,
    @Json(name = "last_out_time")
    val lastOutTime: String,
    @Json(name = "last_seen")
    val lastSeen: Int,
    @Json(name = "last_seen_time")
    val lastSeenTime: String,
    val manager: String,
    @Json(name = "n_ballot")
    val nBallot: Int,
    @Json(name = "n_delegation")
    val nDelegation: Int,
    @Json(name = "n_ops")
    val nOps: Int,
    @Json(name = "n_ops_failed")
    val nOpsFailed: Int,
    @Json(name = "n_origination")
    val nOrigination: Int,
    @Json(name = "n_proposal")
    val nProposal: Int,
    @Json(name = "n_tx")
    val nTx: Int,
    @Json(name = "next_bake_height")
    val nextBakeHeight: Int,
    @Json(name = "next_bake_priority")
    val nextBakePriority: Int,
    @Json(name = "next_bake_time")
    val nextBakeTime: String,
    @Json(name = "next_endorse_height")
    val nextEndorseHeight: Int,
    @Json(name = "next_endorse_time")
    val nextEndorseTime: String,
    val pubkey: String,
    @Json(name = "rich_rank")
    val richRank: Int,
    val rolls: Int,
    @Json(name = "slots_endorsed")
    val slotsEndorsed: Int,
    @Json(name = "slots_missed")
    val slotsMissed: Int,
    @Json(name = "spendable_balance")
    val spendableBalance: Double,
    @Json(name = "staking_balance")
    val stakingBalance: Double,
    @Json(name = "token_gen_max")
    val tokenGenMax: Int,
    @Json(name = "token_gen_min")
    val tokenGenMin: Int,
    @Json(name = "total_balance")
    val totalBalance: Double,
    @Json(name = "total_burned")
    val totalBurned: Double,
    @Json(name = "total_delegations")
    val totalDelegations: Int,
    @Json(name = "total_fees_earned")
    val totalFeesEarned: Double,
    @Json(name = "total_fees_paid")
    val totalFeesPaid: Double,
    @Json(name = "total_lost")
    val totalLost: Int,
    @Json(name = "total_received")
    val totalReceived: Double,
    @Json(name = "total_rewards_earned")
    val totalRewardsEarned: Double,
    @Json(name = "total_sent")
    val totalSent: Double,
    @Json(name = "traffic_rank")
    val trafficRank: Int,
    @Json(name = "unclaimed_balance")
    val unclaimedBalance: Double
)