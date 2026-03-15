package com.abhishekyadav.portfolioadmin.ui.screens.projects

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
import com.abhishekyadav.portfolioadmin.viewmodel.ProjectViewModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull

@Composable
fun ProjectsScreen(navController: NavController) {
    val context = navController.context
    val viewModel = remember { ProjectViewModel(context) }
    val projects by viewModel.projects.collectAsState()
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var sourceCode by remember { mutableStateOf("") }
    var demoUrl by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        imageUri = uri
    }
    LaunchedEffect(true) {
        viewModel.loadProjects()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Add Project", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Project Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = sourceCode,
            onValueChange = { sourceCode = it },
            label = { Text("Source Code URL") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = demoUrl,
            onValueChange = { demoUrl = it },
            label = { Text("Demo URL") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { imagePicker.launch("image/*") }
        ) {
            Text("Select Image")
        }
        Spacer(modifier = Modifier.height(10.dp))


        Button(
            onClick = {

                if (title.isBlank()) {
                    return@Button
                }
                val imagePart = imageUri?.let { uri ->

                    val inputStream = context.contentResolver.openInputStream(uri)
                    val bytes = inputStream!!.readBytes()

                    val type = context.contentResolver.getType(uri) ?: "image/*"

                    val requestFile =
                        bytes.toRequestBody(type.toMediaTypeOrNull())

                    val fileName = "project_" + System.currentTimeMillis() + ".jpg"

                    MultipartBody.Part.createFormData(
                        "image",
                        fileName,
                        requestFile
                    )
                }
                viewModel.addProject(
                    title.toRequestBody("text/plain".toMediaType()),
                    description.toRequestBody("text/plain".toMediaType()),
                    sourceCode.toRequestBody("text/plain".toMediaType()),
                    demoUrl.toRequestBody("text/plain".toMediaType()),
                    imagePart
                )

                title = ""
                description = ""
                sourceCode = ""
                demoUrl = ""
                imageUri = null
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Project")
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text("Projects List", style = MaterialTheme.typography.titleLarge)
        LazyColumn {
            items(projects) { project ->
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
                            Text(project.projectTitle)
                            Text(project.projectDescription)
                        }
                        IconButton(
                            onClick = {
                                viewModel.deleteProject(project.id)
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
