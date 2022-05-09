package com.example.memeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.memeapp.ui.theme.MemeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    var problemToDisplay by remember { mutableStateOf(TextFieldValue()) }
                    Column {
                        TextField(
                            value = problemToDisplay,
                            onValueChange = {problemToDisplay = it} ,
                            placeholder = { Text(text = "What Exercise do you want to view?") }
                        )
                        DisplayedProblem(problemToDisplay.text)
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayedProblem(exercise: String) {
    when(exercise) {
        "StepArray" -> StepArray()
        "FibonacciArray" -> FibonacciArray()
        else -> {
            Text(text = "Invalid Exercise")
        }
    }
}

@Composable
fun StepArray()
{
    Column(Modifier.padding(16.dp)) {
        var max by remember { mutableStateOf(TextFieldValue("")) }
        var step by remember { mutableStateOf(TextFieldValue("")) }
        Row {
            TextField(
                value = max,
                onValueChange = {max = it} ,
                placeholder = { Text(text = "max") }
            )
            TextField(
                value = step,
                onValueChange = {step = it} ,
                placeholder = { Text(text = "step") }
            )
        }

        val maxNull = max.text.toIntOrNull()
        val stepNull = step.text.toIntOrNull()
        if ((maxNull != null) && (stepNull != null)) {
            val maxInt = maxNull.toInt()
            val stepInt = stepNull.toInt()
            Text(text = createStepArray(maxInt, stepInt).joinToString(", "))
        }
        else {
            Text(text = "Invalid Input")
        }

    }
}

fun createStepArray(max: Int, step: Int): Array<Int> {
    return Array(max / step) { i -> (i+1) * step }
}

@Composable
fun FibonacciArray() {
    var size by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = size,
        onValueChange = {size = it} ,
        placeholder = { Text(text = "size") }
    )

    val sizeNull = size.text.toIntOrNull()
    if (sizeNull != null) {
        val sizeInt = sizeNull.toInt()
        val testArray = Array(sizeInt) {1}
        var t1 = 0
        var t2 = 1
        for (i in 1 until sizeInt) {
            val sum = t1+t2
            testArray[i] = sum
            t1 = t2
            t2 = sum
        }
        Text(text = testArray.joinToString(", "))
    }
    else {
        Text(text = "Invalid Input")
    }

}