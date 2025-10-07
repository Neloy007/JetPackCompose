package com.example.jetpackpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackpractice.ui.theme.JetpackPracticeTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackPracticeTheme {
                HomeScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Open Drawer */ }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.Black)
                    }
                },
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .clickable { /* Profile Click */ },
                        contentScale = ContentScale.Crop
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Title
            Text(
                text = buildAnnotatedString {
                    append("Fast and ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color(0xFFEA4335))) {
                        append("Delicious")
                    }
                    append(" Food")
                },
                fontSize = 28.sp,
                lineHeight = 32.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Search your food...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Collections
            val collections = listOf("Burger", "Pizza", "Sushi", "Kebab", "Shawarma")
            var selectedCategory by remember { mutableStateOf("Burger") }

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(vertical = 8.dp)
            ) {
                collections.forEach { category ->
                    val isSelected = category == selectedCategory
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = if (isSelected) Color(0xFFEA4335) else Color.LightGray,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable { selectedCategory = category }
                    ) {
                        Text(
                            text = category,
                            color = if (isSelected) Color.White else Color.Black,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Food Items (Horizontal Scroll)
            val foods = listOf(
                Triple("Cheezy Burger", "$5.99", R.drawable.ceezyburger),
                Triple("Hamburger", "$4.99", R.drawable.burger),
                Triple("Double Patty", "$6.49", R.drawable.doublepetty),
            )

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(vertical = 8.dp)
            ) {
                foods.forEach { (name, price, img) ->
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .width(180.dp)
                            .height(220.dp)
                            .padding(end = 12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.8f))
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Image(
                                painter = painterResource(id = img),
                                contentDescription = name,
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Text(text = name, fontWeight = FontWeight.Bold)
                            Text(text = price, color = Color(0xFFEA4335), fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    JetpackPracticeTheme {
        HomeScreen()
    }
}
