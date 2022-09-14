package com.example.socialquiz.screen.home

import android.app.Application
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import com.example.socialquiz.common.ListsCard
import com.example.socialquiz.common.LoginView
import com.example.socialquiz.responseItems.UserOrgsItem
import com.example.socialquiz.ui.theme.Themee


@Preview
@Composable
fun Preview(){
    Column {
        TopView()
    }
}


@Composable
fun ColumnScope.TopView(){
    Column(
        Modifier.weight(.2f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        var showDialog by remember { mutableStateOf(false) }
        AnimatedVisibility(visible = showDialog) {
            Dialog(onDismissRequest = { showDialog = !showDialog }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(.7f)
                        .wrapContentHeight()
                ) {
                    LazyColumn{
                        items(10) {
                            Text(text = "Number $it")
                        }
                    }
                }
            }
        }
        Column( Modifier.weight(.1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    Modifier.clickable(
                        onClick = { showDialog = true }
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    IconButton(onClick = {
                        showDialog = true
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.AddCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSecondary
                        )
                    }
                    ClickableText(text = AnnotatedString("Org Title"),
                        overflow = TextOverflow.Ellipsis,
                        onClick = {}
                    )
                }

                IconButton(onClick = {
                    Themee.isDark.value = !Themee.isDark.value
                }) {
                    Icon(
                        imageVector = Icons.Rounded.AccountCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }
        Column(
            Modifier
                .fillMaxWidth()
                .weight(.1f)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Red,
                            Color.White,
                            Color.Green
                        )
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "center")
        }
    }
}



@Composable
fun HomeScreen(

    padding : PaddingValues = PaddingValues(),
    viewModel: HomeScreenViewModel = HomeScreenViewModel(Application),
    cardclickListener : (UserOrgsItem)-> Unit = {}

) {

    Column(
        Modifier
            .fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Top) {



        AnimatedVisibility(visible = !viewModel.isLoggedIn) {
            Dialog(onDismissRequest = {viewModel.isLoggedIn  }) {
                LoginView{}
            }
        }


       TopView()

        Column(
            Modifier.weight(.8f),
            verticalArrangement = Arrangement.spacedBy((-60).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Card(
                Modifier
                    .fillMaxWidth(.9f)
                    .height(80.dp)
                    .weight(.2f)
                    .zIndex(1f),
                backgroundColor = Color.White,
                shape = RoundedCornerShape(12.dp)
            ) {


                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                   verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    var textState by remember { mutableStateOf("") }
                    val maxLength = 110
                    val lightBlue = Color.Red
                    val focusRequester = remember { FocusRequester() }
                    var isLoading: Boolean by remember { mutableStateOf(false) }

                    AnimatedVisibility(visible = !isLoading) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(.8f),
                            text = "Continue Where you Left",
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 20.sp
                        )
                    }
                    AnimatedVisibility(visible = isLoading) {
                        TextField(
                        modifier = Modifier
                            .fillMaxWidth(.8f)
                            .focusRequester(focusRequester),
                        value = textState,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color(220, 220, 229),
                            cursorColor = Color.Black,
                            disabledLabelColor = lightBlue,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        onValueChange = {
                            if (it.length <= maxLength) textState = it
                        },
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        trailingIcon = {
                            if (textState.isNotEmpty()) {
                                IconButton(
                                    onClick = {
                                        textState = ""
                                    }) {
                                    Icon(
                                        imageVector = Icons.Outlined.Close,
                                        contentDescription = null
                                    )

                                }
                            }
                        }
                    )

                    }
                    IconButton(modifier= Modifier
                        .fillMaxWidth(.4f),
                        onClick = {
                            isLoading = !isLoading; }) {
                        Icon(
                            imageVector = if(!isLoading) Icons.Outlined.Search else Icons.Default.Clear ,
                            contentDescription = null
                        )
                    }
                }

            }
            Card(
                modifier = Modifier
                    .weight(.8f)
                    .padding(start = 2.dp, end = 2.dp),
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                backgroundColor = Color(217, 218, 227)
            )
            {
                ListsCard(viewModel,padding,paddingtop = 60.dp){cardclickListener(it)}
            }

        }
    }


}