package com.abhishekyadav.portfolioadmin.ui.screens.messages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abhishekyadav.portfolioadmin.viewmodel.MessageViewModel

@Composable
fun MessagesScreen(navController: NavController) {


    val context = navController.context
    val viewModel = remember { MessageViewModel(context) }
    val messages by viewModel.messages.collectAsState()

    LaunchedEffect(true) {
        viewModel.loadMessages()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Contact Messages", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(messages, key = { it.id }) { message ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {

                        Text("Email: ${message.senderEmail}")
                        Text("Subject: ${message.subject}")

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(message.message)

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            IconButton(
                                onClick = {
                                    viewModel.deleteMessage(message.id)
                                }
                            ) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete")
                            }

                        }

                    }

                }

            }

        }

    }


}
