package com.abhishekyadav.portfolioadmin.data.repository

import com.abhishekyadav.portfolioadmin.data.api.ApiService
import com.abhishekyadav.portfolioadmin.data.model.Message
class MessageRepository(private val api: ApiService) {
    suspend fun getMessages(): List<Message> {
        return api.getMessages()
    }
    suspend fun deleteMessage(id: Long) {
        api.deleteMessage(id)
    }
}
