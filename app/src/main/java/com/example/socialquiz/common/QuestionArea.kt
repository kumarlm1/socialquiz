package com.example.socialquiz.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import com.skydoves.landscapist.glide.GlideImage



data class QuestionJson(
    val id : Int,
    val question : String,
    val answers : List<Options>,
    val answer : Int
)
data class Options(
    val id: Int,
    val option : String,
    var isSelected : Boolean = false
)

val Question1 =  QuestionJson(1,"1.The graphs of two linear equations ax+by = c and " +
        "bx-ay = c , where a,b and c are not equal to zero.bx-ay = c , where a,b and c are not equal to zero.bx-ay = c , where a,b and c are not equal to zero.bx-ay = c , where a,b and c are not equal to zero.bx-ay = c , where a,b and c are not equal to zero.bx-ay = c , where a,b and c are not equal to zero.",
    listOf(
        Options(1,"1.Are Parallelbx-ay = c , where a,b and c are not equal to zero."),Options(2,"Perpendicularbx-ay = c , where a,b and c are not equal to zero."),
        Options(3,"Intersect at Two pointsbx-ay = c , where a,b and c are not equal to zero."), Options(4,"https://media.istockphoto.com/photos/and-a-concept-yellow-question-mark-glowing-amid-black-question-marks-picture-id1305169776?s=612x612")),2)
val Question2 =QuestionJson(1,"https://media.istockphoto.com/photos/and-a-concept-yellow-question-mark-glowing-amid-black-question-marks-picture-id1305169776?s=612x612",
    listOf(
        Options(1,"2.Are Parallelbx-ay = c , where a,b and c are not equal to zero."),Options(2,"Perpendicularbx-ay = c , where a,b and c are not equal to zero."),
        Options(3,"Intersect at Two pointsbx-ay = c , where a,b and c are not equal to zero."), Options(4,"Intersect at One Point Intersect at One Point")),1)
val Question3 =QuestionJson(1,"1.The graphs of two linear equations ax+by = c and " +
        "bx-ay = c , where a,b and c are not equal to zero.bx-ay = c , where a,b and c are not equal to zero.bx-ay = c , where a,b and c are not equal to zero.bx-ay = c , where a,b and c are not equal to zero.bx-ay = c , where a,b and c are not equal to zero.bx-ay = c , where a,b and c are not equal to zero.",
    listOf(
        Options(1,"https://media.istockphoto.com/photos/and-a-concept-yellow-question-mark-glowing-amid-black-question-marks-picture-id1305169776?s=612x612"),Options(2,"Perpendicularbx-ay = c , where a,b and c are not equal to zero."),
        Options(3,"Intersect at Two pointsbx-ay = c , where a,b and c are not equal to zero."), Options(4,"Intersect at One Point Intersect at One Point")),3)




@Composable
fun  QuestionArea(
    fontSize : TextUnit,
    question : String,
    options : List<Options>,
    answer : Int,
    showAnswer: Boolean,
    answerclicked : (Int)-> Unit
) {
    Box {
        if(!question.contains("http",true))
        Text(
            text = question,
            color = Color(226, 227, 229),
            style = TextStyle(
                fontSize = fontSize,
                fontWeight = FontWeight.Bold
            )
        )
        else
        com.skydoves.landscapist.glide.GlideImage(imageModel = question)
    }

    Spacer(modifier = Modifier.height(15.dp))
   AnswersBlock(options = options, answer = answer, fontSize = fontSize, showAnswer = showAnswer){
       answerclicked(it)
   }
    Spacer(modifier = Modifier.height(20.dp))



}
@Composable
fun AnswersBlock(
    options : List<Options>,
    answer : Int,
    fontSize : TextUnit,
    showAnswer: Boolean,
    clickedListener : (Int) -> Unit,
){

    options.forEachIndexed { index, it ->

        AnswerBlock(it = it, showAnswer = showAnswer, fontSize = fontSize, isAnswer = it.id == answer,index = index){
            clickedListener(it)
        }
        Spacer(modifier = Modifier.height(5.dp))
    }
}


@Composable
fun AnswerBlock(
    it : Options,
    showAnswer: Boolean,
    fontSize: TextUnit,
    isAnswer : Boolean,
    index : Int,
    isClickedListener : (Int)-> Unit
){
    var toggleImage by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(if (!showAnswer) Modifier.clickable(
                onClick = {
                    isClickedListener(index)
                }
            ) else Modifier),
        elevation = if(showAnswer && isAnswer) 20.dp  else 2.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color.Transparent,
        border = BorderStroke(2.dp,
            if(showAnswer && isAnswer) Color.Green
            else if (it.isSelected  && !isAnswer) Color.Red
            else Color(226, 227, 229)
        ),

        ) {

        Row(
            modifier = Modifier.padding(all = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if(!it.option.contains("http",true))
            Text( modifier = Modifier.weight(.8f), text = buildAnnotatedString { append(it.option) },
                style = TextStyle(
                    color = Color(226, 227, 229),
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold
                ),
            )
            else
                com.skydoves.landscapist.glide.GlideImage(
                    modifier = Modifier.clickable(
                        onClick = { toggleImage = true }
                    ),
                    imageModel = it.option)

            Icon(
                imageVector = (
                        if (showAnswer && isAnswer) Icons.Outlined.Done
                        else if (it.isSelected && !isAnswer) Icons.Outlined.Clear
                        else Icons.Outlined.CheckCircle
                        ),
                contentDescription ="",
            tint = if(showAnswer && isAnswer) Color.Green
            else if(it.isSelected && !isAnswer) Color.Red
                else Color.LightGray
            )
        }


    }
    
    AnimatedVisibility(visible = toggleImage) {

        Dialog(onDismissRequest = {  }) {
            Column(Modifier.fillMaxWidth(),
            ) {
                Button(modifier = Modifier.weight(2f),onClick = { toggleImage = false }) {
                    Text(text = "Close")
                }
                com.skydoves.landscapist.glide.GlideImage(
                    modifier = Modifier.weight(8f),
                    imageModel = it.option)

            }
        }


    }
    
    
    
}
