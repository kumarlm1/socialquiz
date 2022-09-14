package com.example.socialquiz.common

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.socialquiz.QuestionActivity
import com.example.socialquiz.screen.home.HomeScreenViewModel


@Preview
@Composable
fun SubCategoryScreen(
    padding: PaddingValues = PaddingValues(),
    viewModel: HomeScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    backbuttonListener: ()->Unit = {},
){
    val context = LocalContext.current

    Column(
        Modifier.background(MaterialTheme.colors.onSecondary)
    ) {
        Row(
            Modifier
                .background(MaterialTheme.colors.onSecondary)
                .weight(.1f),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(modifier = Modifier.weight(.2f),onClick = {
                backbuttonListener()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription =null,
                    tint = MaterialTheme.colors.primary)
            }
            Text(modifier = Modifier.weight(.8f),
                text = "Choose Category",
                color = MaterialTheme.colors.primary)
        }
        Card(
            Modifier
                .weight(.8f)
                .padding(start = 5.dp, end = 5.dp),
        shape = RoundedCornerShape(topStartPercent = 5,topEndPercent = 5),
        ) {
            ListsCard(viewModel,padding = padding){
                val intent = Intent(context,QuestionActivity::class.java)
                intent.putExtra("title",it.org.id)
                context.startActivity(intent)
            }


        }

    }


}