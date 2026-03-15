package com.abhishekyadav.portfolioadmin.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abhishekyadav.portfolioadmin.data.model.Resume

@Composable
fun ResumeItem(
    resume: Resume,
    onDelete: (Long) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(resume.resumeTitle)

            IconButton(
                onClick = { onDelete(resume.id) }
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }

        }

    }

}
