package com.example.lgonzalezfinanceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lgonzalezfinanceapp.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LGonzalezFinanceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val transactions = listOf(
        Transaction("BodegaAurrera", "Juguetes", 67.67, "10:30 AM", Icons.Default.ShoppingCart),
        Transaction("Oxxo Gas", "LaDeLimon", 200.00, "12:15 PM", Icons.Default.ShoppingCart),
        Transaction("Cafeteria Uni", "Quekas", 23.75, "8:00 AM", Icons.Default.ShoppingCart),
        Transaction("Amazon", "Rtx5090", 1200000.0, "3:45 PM", Icons.Default.ShoppingCart),
        Transaction("Steam", "Mewgenics", 254.99, "2:00 PM", Icons.Default.ShoppingCart),
        Transaction("SushiRoll", "chuchi", 230.0, "7:30 PM", Icons.Default.ShoppingCart)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HeaderSection(userName = "Leonel")
        Spacer(modifier = Modifier.height(24.dp))
        SummaryCardsSection()
        Spacer(modifier = Modifier.height(24.dp))
        TransactionsSection(transactions)
    }
}

@Composable
fun HeaderSection(userName: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Hola $userName",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Bienvenido",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
        IconButton(onClick = { }) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
        }
    }
}

@Composable
fun SummaryCardsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SummaryCardItem(
            card = SummaryCard(
                title = "Actividad",
                subtitle = "de la Semana",
                amount = "",
                backgroundColor = CardGreen,
                icon = Icons.Default.Face
            ),
            modifier = Modifier.weight(1f).fillMaxHeight()
        )

        Column(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SummaryCardItem(
                card = SummaryCard(
                    title = "Ventas",
                    amount = "$911.11",
                    backgroundColor = CardPeach
                ),
                modifier = Modifier.weight(1f).fillMaxWidth()
            )
            SummaryCardItem(
                card = SummaryCard(
                    title = "Ganancias",
                    amount = "$67.67",
                    backgroundColor = CardLavender
                ),
                modifier = Modifier.weight(1f).fillMaxWidth()
            )
        }
    }
}

@Composable
fun SummaryCardItem(card: SummaryCard, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = card.backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (card.icon != null) {
                Icon(
                    imageVector = card.icon,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            Text(
                text = card.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            if (card.subtitle != null) {
                Text(
                    text = card.subtitle,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
            if (card.amount.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = card.amount,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun TransactionsSection(transactions: List<Transaction>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Transactions",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            TextButton(onClick = { }) {
                Text(text = "See All", color = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(transactions) { transaction ->
                TransactionItem(transaction)
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = transaction.icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = transaction.establishmentName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = transaction.category,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                val amountText = if (transaction.amount < 0) "$-${-transaction.amount}" else "$${transaction.amount}"
                Text(
                    text = amountText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = transaction.time,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LGonzalezFinanceAppTheme {
        HomeScreen()
    }
}
