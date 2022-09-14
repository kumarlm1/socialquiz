package com.example.socialquiz

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.core.graphics.scaleMatrix
import androidx.lifecycle.ViewModel
import com.example.socialquiz.common.Options
import com.example.socialquiz.common.Question1
import com.example.socialquiz.common.Question2
import com.example.socialquiz.common.Question3
import kotlin.reflect.KProperty

class QuestionViewModel : ViewModel() {

    var testMode : Boolean = false
    var questions = mutableListOf(Question1, Question2, Question3,Question1.copy(), Question2.copy(), Question3.copy(),Question1, Question2, Question3,Question1, Question2, Question3)


    // States
    var question by mutableStateOf("")
    var options by mutableStateOf(listOf<Options>())
    var answer by mutableStateOf(-1)
    var showAnswer by mutableStateOf(false)

    var currentQuestionIndex by mutableStateOf(0)
    var score by  mutableStateOf(0)
    var scoresArray = mutableStateListOf<Int>()



    // Listeners
    fun setquestion(q : String){
        question = q
        scoresArray = mutableStateListOf(0)
    }
    fun setoptions(q : List<Options>){
        options = q
    }
    fun setanswer(q : Int){
        answer = q
    }


    fun onOptionClicked(index : Int){
        if(answer == options[index].id) score++
        options[index].isSelected = true
        if(!testMode)
        showAnswer = true
    }

    fun onNextClicked(){
        if(!testMode)
        showAnswer = false
        if(currentQuestionIndex < questions.size)currentQuestionIndex++
        setUpQuiz()
        println("click called")
    }

    fun onPreviousClicked(){
        if(!testMode)
        showAnswer = true
        if (currentQuestionIndex != 0) currentQuestionIndex--
        setUpQuiz()
    }
    fun onQuitClicked(){

    }





    fun getscoresArray() = scoresArray

    init {
       setUpQuiz()
    }

    fun setUpQuiz(){
        setquestion("$currentQuestionIndex-->"+questions[currentQuestionIndex].question)
        setoptions(questions[currentQuestionIndex].answers)
        setanswer(questions[currentQuestionIndex].answer)
    }



}








