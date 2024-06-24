package com.example.recall.bnt

/**
 * Data class for the Boston Naming Test (BNT)
 */
data class Response(
    val objectId: Int,
    val responseText: String,
    val responseTime: Long,
    val cueUsed: Int,
)
