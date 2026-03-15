package com.abhishekyadav.portfolioadmin.ui.screens.resume

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abhishekyadav.portfolioadmin.viewmodel.ResumeViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull

@Composable
fun ResumeScreen(navController: NavController) {

    val context = navController.context
    val viewModel = remember { ResumeViewModel(context) }

    val resume by viewModel.resumes.collectAsState()

    var title by remember { mutableStateOf("") }
    var fileUri by remember { mutableStateOf<Uri?>(null) }

    val filePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->

        uri?.let {

            val type = context.contentResolver.getType(it)

            if (type == "application/pdf") {
                fileUri = it
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.loadResumes()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Upload Resume", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Resume Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                filePicker.launch("application/pdf")
            }
        ) {
            Text("Select PDF")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {

                if (title.isBlank()) return@Button
                if (fileUri == null) return@Button

                val inputStream = context.contentResolver.openInputStream(fileUri!!)
                val bytes = inputStream!!.readBytes()


                val type = context.contentResolver.getType(fileUri!!) ?: "application/pdf"

                val requestFile =
                    bytes.toRequestBody(type.toMediaTypeOrNull())

                val fileName = "resume_" + System.currentTimeMillis() + ".pdf"

                val filePart = MultipartBody.Part.createFormData(
                    "file",
                    fileName,
                    requestFile
                )

                val titlePart =
                    title.toRequestBody("text/plain".toMediaTypeOrNull())

                viewModel.uploadResume(
                    filePart,
                    titlePart
                )

                title = ""
                fileUri = null
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload Resume")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Current Resume", style = MaterialTheme.typography.titleLarge)

        resume?.let {

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

                    Text(it.resumeTitle)

                    IconButton(
                        onClick = {
                            viewModel.deleteResume(it.id)
                        }
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }

                }

            }

        }

    }

}