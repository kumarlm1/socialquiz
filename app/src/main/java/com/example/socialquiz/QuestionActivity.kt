package com.example.socialquiz

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat.getWindowInsetsController
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.socialquiz.common.QuestionUi
import com.example.socialquiz.common.ResultPage
import com.example.socialquiz.ui.theme.SocialQuizTheme

@Suppress("DEPRECATION")
class QuestionActivity : ComponentActivity() {

    private val viewmodel by viewModels<QuestionViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY).also { this.window?.decorView?.systemUiVisibility = it }
        hideSystemBars()
        val title : String = intent.extras?.get("title") as String

        setContent {
            SocialQuizTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var showDialog by remember {
                        mutableStateOf(false) }
                    AnimatedVisibility(visible = !showDialog) {
                        QuestionUi(title,viewmodel){
                            showDialog = true
                        }
                    }

                    AnimatedVisibility(visible = showDialog) {
                        ResultPage{
                            showDialog = false
                        }
                    }
                }
            }
        }
    }


    override fun onPostResume() {
        (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY).also { this.window?.decorView?.systemUiVisibility = it }
        hideSystemBars()
        super.onPostResume()
    }

    override fun onResume() {
        (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY).also { this.window?.decorView?.systemUiVisibility = it }
        hideSystemBars()
        super.onResume()
    }

    override fun onBackPressed() {
    }

    private fun hideSystemBars() {
        val windowInsetsController =
            getWindowInsetsController(window.decorView) ?: return
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())

    }
}

