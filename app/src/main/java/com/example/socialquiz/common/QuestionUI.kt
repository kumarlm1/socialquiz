package com.example.socialquiz.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.socialquiz.QuestionViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import kotlinx.coroutines.launch


private fun Color.Companion.html(Htmlcolor : String) : Color = Color(android.graphics.Color.parseColor(Htmlcolor))


@Composable
fun QuestionUi(
    title : String,
    viewModel: QuestionViewModel,
    quitListener : ()->Unit
){
    val fontSize: TextUnit = 20.sp
    val questions = viewModel.questions
    var score = viewModel.score

    val scores = remember {
        MutableList(questions.size){0}
    }
    var currentQuestionIndex = viewModel.currentQuestionIndex

    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(Color.html("#141A33"))
            .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {

            val (tops, middles, bottoms) = createRefs()

            TopArea(
                modifier = Modifier
                    .constrainAs(tops) {
                        top.linkTo(parent.top)
                    }
                    .padding(vertical = 10.dp),
                fontSize, scores,title,currentQuestionIndex)

            // Middle

            MiddleArea(modifier = Modifier
                .constrainAs(middles) {
                    top.linkTo(tops.bottom)
                    bottom.linkTo(bottoms.top)
                    height = Dimension.fillToConstraints
                }
                .padding(vertical = 10.dp), fontSize = fontSize,
                viewModel = viewModel
                )


            //Bottom
            BottomArea(modifier = Modifier
                .constrainAs(bottoms) {
                    bottom.linkTo(parent.bottom)
                }
                .padding(vertical = 10.dp),
                fontSize = fontSize,
                (currentQuestionIndex < questions.size - 1),
                (currentQuestionIndex > 0),
                {

                        viewModel.onNextClicked()
                    //else
                        //Toast.makeText(context, "sdfsd", Toast.LENGTH_LONG).show()
                },
                { viewModel.onPreviousClicked() }){
                    quitListener()
            }

        }


}
@Composable
fun TopArea(modifier : Modifier, fontSize : TextUnit  , scores : MutableList<Int>,title: String,currentQuestionIndex : Int){
    val lazyListScrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    ) {
        AndroidView(factory = { context ->
            AdView(context).apply {
                adSize = AdSize.BANNER
                adUnitId = ("ca-app-pub-3150738247035190/1674903500")
                loadAd(AdRequest.Builder().build())
            }})
        Text(
            modifier = Modifier.alpha(.8f),
            text = title,
            color = Color(226, 227, 229),
            style = TextStyle(
                fontSize = fontSize
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = buildAnnotatedString {
                append("Question  ")
                append("${currentQuestionIndex+1}/${scores.size}")
            },
            style = TextStyle(
                color = Color.html("#E2E3E5"),
                fontSize = fontSize
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        val configuration = LocalConfiguration.current

        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp
        var boxWidth = 55.dp
        if(((boxWidth+5.dp)*scores.size) <= screenWidth){
            boxWidth = ((screenWidth - (scores.size*5).dp) / scores.size)
        }
        LazyRow(Modifier.fillMaxWidth(),
                state = lazyListScrollState) {
            itemsIndexed(scores) { idx,it->
                Box(
                    Modifier
                        .height(5.dp)
                        .width(boxWidth)
                        .background(if (it == 1) Color.Green else if (it == -1) Color.Red else Color.Gray)
                )
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
        SideEffect {
            coroutineScope.launch {
                val lastVisibleItemIndex = lazyListScrollState.layoutInfo.visibleItemsInfo.lastIndex + lazyListScrollState.firstVisibleItemIndex
                val focusIndex = if(scores.indexOf(0) <= 0) 0 else scores.indexOf(0)-1
                println("focus : ${lazyListScrollState.firstVisibleItemIndex} $focusIndex $lastVisibleItemIndex")
                if(!((lazyListScrollState.firstVisibleItemIndex <= focusIndex) and (focusIndex <= lastVisibleItemIndex)))
                lazyListScrollState.scrollToItem(focusIndex)
            }
        }



    }
}


@Composable
fun MiddleArea(
    modifier : Modifier,
    fontSize : TextUnit,
    viewModel: QuestionViewModel
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,

        ) {
        QuestionArea(
            fontSize = 20.sp,
            question = viewModel.question,
            options = viewModel.options,
            answer = viewModel.answer,
            showAnswer = viewModel.showAnswer
        ){
            viewModel.onOptionClicked(it)
        }
    }
}

@Composable
fun BottomArea(
    modifier : Modifier,
    fontSize : TextUnit,
    showNext : Boolean,
    showPrevious :Boolean,
    nextListener : ()-> Unit,
    previousListener : ()-> Unit,
    quitListener : ()->Unit
) {
    Row( modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TransparentButton("Quit Quiz",Color.Transparent){ quitListener() }
        if (showPrevious) TransparentButton(text = "Previous",Color(6, 211, 246)) {
            previousListener() }
        if(showNext) TransparentButton(text = "Next",Color(6, 211, 246)) {
            nextListener() }
    }
}

@Composable
fun TransparentButton(
    text : String,
    color : Color,
    onclickListener : ()-> Unit,

){
    Button(onClick = { onclickListener() },
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        )
    { Text(text,color = Color.White) }
}


