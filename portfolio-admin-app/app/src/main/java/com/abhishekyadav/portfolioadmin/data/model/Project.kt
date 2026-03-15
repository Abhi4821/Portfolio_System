
package com.abhishekyadav.portfolioadmin.data.model
data class Project(
    val id: Long,
    val projectTitle: String,
    val projectDescription: String,
    val projectImageUrl: String?,
    val sourceCodeUrl: String?,
    val demoUrl: String?,
    val createdAt: String
)
