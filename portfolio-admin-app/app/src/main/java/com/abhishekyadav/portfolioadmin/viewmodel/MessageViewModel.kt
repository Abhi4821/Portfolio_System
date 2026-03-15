package com.abhishekyadav.portfolioadmin.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishekyadav.portfolioadmin.data.api.RetrofitClient
import com.abhishekyadav.portfolioadmin.data.model.Message
import com.abhishekyadav.portfolioadmin.data.repository.MessageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MessageViewModel(context: Context) : ViewModel() {
    private val api = RetrofitClient.getApiService(context)
    private val repository = MessageRepository(api)
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    fun loadMessages() {
        viewModelScope.launch {
            try {
                _messages.value = repository.getMessages()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun deleteMessage(id: Long) {
        viewModelScope.launch {
            try {
                repository.deleteMessage(id)
                // remove item instantly from UI
                _messages.value = _messages.value.filter { it.id != id }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
