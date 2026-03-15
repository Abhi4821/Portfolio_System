package com.abhishekyadav.portfolioadmin.ui.screens.skills

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import com.abhishekyadav.portfolioadmin.viewmodel.SkillViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

@Composable
fun SkillsScreen(navController: NavController) {
    val context = navController.context
    val viewModel = remember { SkillViewModel(context) }

    val skills by viewModel.skills.collectAsState()

    var name by remember { mutableStateOf("") }
    var level by remember { mutableStateOf("") }
    var certificateUri by remember { mutableStateOf<Uri?>(null) }

    val filePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        certificateUri = uri
    }

    LaunchedEffect(true) {
        viewModel.loadSkills()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Add Skill", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Skill Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = level,
            onValueChange = { level = it },
            label = { Text("Skill Level") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { filePicker.launch("*/*") }
        ) {
            Text("Select Certificate")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {

                if (name.isBlank()) {
                    return@Button
                }
                val part = certificateUri?.let { uri ->
                    val inputStream = context.contentResolver.openInputStream(uri)
                    val fileBytes = inputStream!!.readBytes()
                    val type = context.contentResolver.getType(uri) ?: "*/*"
                    val requestFile =
                        fileBytes.toRequestBody(type.toMediaTypeOrNull())
                    MultipartBody.Part.createFormData(
                        "certificate",
                        "certificate.jpg",
                        requestFile
                    )
                }
                viewModel.addSkill(
                    name.toRequestBody("text/plain".toMediaTypeOrNull()),
                    level.toRequestBody("text/plain".toMediaTypeOrNull()),
                    part
                )
                name = ""
                level = ""
                certificateUri = null
            },
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Add Skill")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text("Skills List", style = MaterialTheme.typography.titleLarge)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(skills) { skill ->
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
                        Column {
                            Text(skill.skillName)
                            Text(skill.skillLevel)
                        }
                        IconButton(
                            onClick = {
                                viewModel.deleteSkill(skill.id)
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
