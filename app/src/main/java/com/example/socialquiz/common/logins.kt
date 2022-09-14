package com.example.socialquiz.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialquiz.screen.home.HomeScreenViewModel

@Composable
fun LoginView(
    viewModel: HomeScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    submitListener : ()->Unit,

){


    Column(
        Modifier
            .fillMaxWidth(.8f)
            .fillMaxHeight(.8f)
            .background(brush = Brush.linearGradient(
                colors = listOf(
                    Color(0x66FFFFFF),
                    Color(0x66FFFFF6)
                )
            ))
            .alpha(.6f)
            ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier=Modifier.weight(1f),text="Hello  back")
        Inputs(viewModel)

    }






}


@Composable
fun ColumnScope.Inputs(
    viewModel: HomeScreenViewModel
){
    Column(
        Modifier
            .fillMaxWidth(.9f)
            .background(Color.Transparent)
            .padding(5.dp)
            .weight(5f),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        var email by remember{ mutableStateOf("kumar2@gmail.com") }
        var password by remember{ mutableStateOf("kumar2") }


        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            leadingIcon = { Icon(imageVector = Icons.Outlined.Email, contentDescription = null) },
            singleLine = true,
            shape = RoundedCornerShape(20),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(220, 220, 229),
                cursorColor = Color.Black,
                disabledLabelColor = Color.Red,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            leadingIcon = { Icon(imageVector = Icons.Outlined.Lock, contentDescription = null) },
            trailingIcon ={ Icon(imageVector = Icons.Outlined.Edit, contentDescription = null) },
            visualTransformation =  PasswordVisualTransformation(),
            singleLine = true,
            shape = RoundedCornerShape(20),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(220, 220, 229),
                cursorColor = Color.Black,
                disabledLabelColor = Color.Red,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "forget Password")
        
        Spacer(modifier = Modifier.height(10.dp))

        AnimatedVisibility(visible = !viewModel.isSubmitted) {
            ExtendedFloatingActionButton(
                modifier = Modifier.fillMaxWidth(),
                text = { Text(text = "Submit") },
                onClick = { viewModel.onSubmitButton();viewModel.Login(email,password)  },
                shape = RoundedCornerShape(50),
            )
        }

        AnimatedVisibility(visible = viewModel.isSubmitted) {
            CircularProgressIndicator(
                strokeWidth = 2.dp,
                color = MaterialTheme.colors.onSecondary
            )

        }

        Spacer(modifier = Modifier.height(10.dp))



    }
}


@Preview
@Composable
fun Pre(){
    LoginView {

    }
}