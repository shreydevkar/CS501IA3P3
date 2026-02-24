package com.example.cs501ia3p3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Label
import androidx.compose.material3.*
import androidx.compose.material3.Label
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cs501ia3p3.ui.theme.CS501IA3P3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CS501IA3P3Theme {
                TagBrowserScreen()
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TagBrowserScreen() {

    val allTags = listOf(
        "Kotlin", "Java", "Compose", "Android",
        "UI Design", "Backend", "Machine Learning",
        "Data Science", "Cloud", "Startup",
        "Finance", "Algorithms", "Open Source"
    )

    var selectedTags by remember { mutableStateOf(setOf<String>()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tag Browser") }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            /* ---------------- FLOWROW SECTION ---------------- */

            Text(
                text = "Browse Tags",
                style = MaterialTheme.typography.titleLarge
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                allTags.forEach { tag ->

                    val isSelected = selectedTags.contains(tag)

                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            selectedTags =
                                if (isSelected) selectedTags - tag
                                else selectedTags + tag
                        },
                        label = { Text(tag) },
                        leadingIcon = null
                    )
                }
            }

            /* ---------------- FLOWCOLUMN SECTION ---------------- */

            HorizontalDivider()

            Text(
                text = "Selected Tags",
                style = MaterialTheme.typography.titleMedium
            )

            Surface(
                modifier = Modifier.fillMaxWidth(),
                tonalElevation = 2.dp
            ) {

                if (selectedTags.isEmpty()) {
                    Text(
                        text = "No tags selected",
                        modifier = Modifier.padding(16.dp)
                    )
                } else {

                    FlowColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        maxItemsInEachColumn = 3
                    ) {
                        selectedTags.forEach { tag ->

                            AssistChip(
                                onClick = {},
                                label = { Text(tag) },
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                            selectedTags = selectedTags - tag
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Label,
                                            contentDescription = "Remove"
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }

            /* ---------------- CLEAR BUTTON ---------------- */

            if (selectedTags.isNotEmpty()) {
                Button(
                    onClick = { selectedTags = emptySet() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Clear All")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTagBrowser() {
    CS501IA3P3Theme {
        TagBrowserScreen()
    }
}