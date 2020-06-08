package com.pavelkazancev02.teztest.data.value_object.account_op


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Op(
    val block: String,
    val branch: String,
    @Json(name = "branch_depth")
    val branchDepth: Int,
    @Json(name = "branch_height")
    val branchHeight: Int,
    @Json(name = "branch_id")
    val branchId: Int,
    val burned: Double,
    val counter: Int,
    val cycle: Int,
    val `data`: String = "",
    @Json(name = "days_destroyed")
    val daysDestroyed: Double,
    val `delegate`: String = "",
    val deposit: Int,
    @Json(name = "entrypoint_id")
    val entrypointId: Int,
    val fee: Double,
    @Json(name = "gas_limit")
    val gasLimit: Int,
    @Json(name = "gas_price")
    val gasPrice: Double,
    @Json(name = "gas_used")
    val gasUsed: Int,
    @Json(name = "has_data")
    val hasData: Boolean,
    val hash: String,
    val height: Int,
    @Json(name = "is_contract")
    val isContract: Boolean,
    @Json(name = "is_implicit")
    val isImplicit: Boolean,
    @Json(name = "is_internal")
    val isInternal: Boolean,
    @Json(name = "is_success")
    val isSuccess: Boolean,
    @Json(name = "op_c")
    val opC: Int,
    @Json(name = "op_i")
    val opI: Int,
    @Json(name = "op_l")
    val opL: Int,
    @Json(name = "op_n")
    val opN: Int,
    @Json(name = "op_p")
    val opP: Int,
    val `receiver`: String = "delegated",
    val reward: Int,
    @Json(name = "row_id")
    val rowId: Int,
    val sender: String,
    val status: String,
    @Json(name = "storage_limit")
    val storageLimit: Int,
    @Json(name = "storage_paid")
    val storagePaid: Int,
    @Json(name = "storage_size")
    val storageSize: Int,
    val time: String,
    val type: String,
    val volume: Double
)