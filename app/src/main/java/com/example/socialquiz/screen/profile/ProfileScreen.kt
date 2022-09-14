package com.example.socialquiz.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun ProfileScreen(
    padding : PaddingValues = PaddingValues()
) {
    Box(modifier = Modifier.fillMaxSize().background(Color.Red).padding(padding.calculateBottomPadding()),
        contentAlignment = Alignment.Center) {
        Text(
            text ="ProfileScreen",
            color= Color.White,
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold,)
    }
}