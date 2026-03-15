package com.abhishekyadav.portfolioadmin.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.abhishekyadav.portfolioadmin.data.model.Message

@Composable
fun MessageItem(
    message: Message,
    onDelete: (Long) -> Unit
) {

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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                IconButton(
                    onClick = { onDelete(message.id) }
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }

            }

        }

    }

}

