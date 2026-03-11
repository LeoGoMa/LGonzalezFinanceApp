package com.example.lgonzalezfinanceapp

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class User(
    val name: String,
    val currentBalance: Double
)

data class SummaryCard(
    val title: String,
    val subtitle: String? = null,
    val amount: String,
    val backgroundColor: Color,
    val icon: ImageVector? = null
)

data class Transaction(
    val establishmentName: String,
    val category: String,
    val amount: Double,
    val time: String,
    val icon: ImageVector
)
