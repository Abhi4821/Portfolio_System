package com.abhishekyadav.portfolioadmin.data.model

data class Skill(
    val id: Long,
    val skillName: String,
    val skillLevel: String,
    val certificateUrl: String?,
    val createdAt: String
)

