package com.abhishekyadav.portfolioadmin.data.model

data class Message(
    val id: Long,
    val senderEmail: String,
    val subject: String,
    val message: String,
    val createdAt: String
)