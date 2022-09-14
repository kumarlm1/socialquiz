package com.example.socialquiz.common


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.socialquiz.responseItems.UserOrgsItem
import com.example.socialquiz.screen.home.HomeScreenViewModel
import kotlin.random.Random

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun CategoryDashboard(){
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White),
    contentAlignment = Alignment.TopStart){
       // ListsCard()
    }
}





@Composable
fun ListsCard(
    viewModel: HomeScreenViewModel,
    padding: PaddingValues = PaddingValues(),
    paddingtop: Dp = 2.dp,
    cardclickListener: (UserOrgsItem)-> Unit
){
    val data = listOf("☕", "🙂", "🥛", "🎉", "📐", "🎯", "🧩", "😄", "🥑")
    val scrollstate = rememberLazyListState()
    val columndata = viewModel.userorgs.chunked(2)
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(top = paddingtop),
        state = scrollstate,

    ){
        println("valuess "+padding.calculateBottomPadding())
        items(columndata) {item->
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(top = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
                Spacer(modifier = Modifier.width(8.dp))
                item.forEach { it ->
                    SingleCard(size = item.size, item = it,padding) { cardclickListener(it) }
                    Spacer(modifier = Modifier.width(8.dp))

                }


            }
        }
    }
}

@Composable
fun RowScope.SingleCard(
    size : Int,
    item :UserOrgsItem,
    padding : PaddingValues = PaddingValues(),
    cardclickListener : (UserOrgsItem)-> Unit
){
    val red by remember {
        mutableStateOf(Random.nextInt(100,255))
    }
    val blue by remember {
        mutableStateOf(Random.nextInt(100,255))
    }
    val green by remember {
        mutableStateOf(Random.nextInt(100,255))
    }


    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .then(
                if (size >= 2)
                    Modifier
                        .weight(1f)
                        .fillMaxWidth(.4f)
                else
                    Modifier
                        .fillMaxWidth(.5f)
                        .padding(bottom = padding.calculateBottomPadding())
            )
            .clickable(
                onClick = { cardclickListener(item) }
            )
            .wrapContentHeight()
            .padding(8.dp)
            .alpha(.8f),
        elevation = 8.dp,
        contentColor = Color.White,
        backgroundColor = Color(red, green, blue)
    ) {
        Column(Modifier.background(Color.Transparent)) {

            Icon(imageVector = Icons.Outlined.Face, contentDescription = "",
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxSize(.4f)
                    .padding(top = 5.dp))
            Text(
                text = item.org.name,
                modifier = Modifier.padding(8.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            OutlinedButton(onClick = { }) {
                Text(text = item.org.visibility)
            }
            OutlinedButton(onClick = { }) {
                Text(text = item.role)
            }
            Text(text = item.joined)
        }

    }
}