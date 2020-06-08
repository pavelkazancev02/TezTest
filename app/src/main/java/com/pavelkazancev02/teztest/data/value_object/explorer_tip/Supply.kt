package com.pavelkazancev02.teztest.data.value_object.explorer_tip


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Supply(
    val activated: Double,
    @Json(name = "active_delegated")
    val activeDelegated: Double,
    @Json(name = "active_staking")
    val activeStaking: Double,
    val burned: Double,
    @Json(name = "burned_double_baking")
    val burnedDoubleBaking: Double,
    @Json(name = "burned_double_endorse")
    val burnedDoubleEndorse: Double,
    @Json(name = "burned_implicit")
    val burnedImplicit: Double,
    @Json(name = "burned_origination")
    val burnedOrigination: Double,
    @Json(name = "burned_seed_miss")
    val burnedSeedMiss: Double,
    val circulating: Double,
    val cycle: Int,
    val delegated: Double,
    val frozen: Double,
    @Json(name = "frozen_deposits")
    val frozenDeposits: Int,
    @Json(name = "frozen_fees")
    val frozenFees: Double,
    @Json(name = "frozen_rewards")
    val frozenRewards: Double,
    val height: Int,
    @Json(name = "inactive_delegated")
    val inactiveDelegated: Double,
    @Json(name = "inactive_staking")
    val inactiveStaking: Double,
    val minted: Double,
    @Json(name = "minted_airdrop")
    val mintedAirdrop: Double,
    @Json(name = "minted_baking")
    val mintedBaking: Double,
    @Json(name = "minted_endorsing")
    val mintedEndorsing: Double,
    @Json(name = "minted_seeding")
    val mintedSeeding: Double,
    @Json(name = "row_id")
    val rowId: Int,
    val staking: Double,
    val time: String,
    val total: Double,
    val unclaimed: Double,
    val unvested: Double,
    val vested: Double
)