package com.example.socialquiz.common

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun ResultPage(
    retryListener : ()-> Unit
){
    val context = LocalContext.current as Activity
    val textColor = MaterialTheme.colors.primary
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ){

        Icon(modifier = Modifier
            .weight(2f)
            .fillMaxSize(),
            imageVector = Icons.Default.Face,
            contentDescription =null,
            tint = Color.Red)
        Column(Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Title",fontSize = 20.sp,color = textColor)
            Row() {
                Text(text = "sub1 > ",color = textColor)
                Text(text = "sub2",color = textColor)
                Text(text = "Sub3",color = textColor)
            }
        }

        Column(
            Modifier
                .fillMaxWidth()
                .weight(5f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(15.dp)) {
            (1..20).forEach {
                ScoresColumn()
                Divider()
            }
        }


        Divider()
        Row(
            Modifier
                .fillMaxWidth()
                .weight(1f,false)
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ExtendedFloatingActionButton(
                text = { Text(text = "Back To Home Page",color = textColor)},
                onClick = { context.finish()  },
                shape = RoundedCornerShape(50),
                icon =  { Icon(imageVector = Icons.TwoTone.Home, contentDescription = null)},

            )

            ExtendedFloatingActionButton(
                text = { Text(text = "Retry",color = textColor)},
                onClick = { retryListener() },
                shape = RoundedCornerShape(50),
                icon =  { Icon(imageVector = Icons.TwoTone.Refresh, contentDescription = null)},
                )
        }
        
        
        
        
    }


}


@Composable
fun ScoresColumn() {
    Row(
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        SingleScoreItem(score="3 / 30",text = "Correct", icon = Icons.Outlined.Check)
        SingleScoreItem(score="3 / 30",text = "Correct", icon = Icons.Outlined.Clear)
        SingleScoreItem(score="3 / 30",text = "Correct", icon = Icons.Outlined.AccountBox)
    }
}





@Composable
fun RowScope.SingleScoreItem(
    score : String,
    text : String,
    icon : ImageVector,

){
    val textColor = MaterialTheme.colors.primary
    Row(
        Modifier.weight(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Icon(imageVector = icon, contentDescription = text,tint=textColor)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = score,color = textColor)
            Text(text = text, overflow = TextOverflow.Ellipsis, maxLines = 1,color = textColor)
        }
    }
}