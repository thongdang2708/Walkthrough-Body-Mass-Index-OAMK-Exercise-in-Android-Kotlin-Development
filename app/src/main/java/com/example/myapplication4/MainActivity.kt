package com.example.myapplication4

import android.graphics.Outline
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication4.ui.theme.MyApplication4Theme
import org.w3c.dom.Text
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplication4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BMI()
                }
            }
        }
    }
}


@Composable
fun BMI() {
    var heightInput = remember {
        mutableStateOf("")
    }

    var weightInput = remember {
        mutableStateOf("")
    }

    var height = heightInput.value.toFloatOrNull() ?: 0.0f;
    var weight = weightInput.value.toIntOrNull() ?: 0
    var bmi = if (weight > 0 && height > 0) weight / (height * height) else 0.0;
    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.body_mass_index_title),
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
        )
        OutlinedTextField(value = heightInput.value, onValueChange = {heightInput.value = it.replace(",", ".")}, singleLine = true, label = {Text(
                    stringResource(R.string.height)
                )}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = weightInput.value, onValueChange = {weightInput.value = it.replace(",", ".")}, singleLine = true, label = {Text(
                    stringResource(R.string.weight)
                )}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
        Text(text = stringResource(R.string.result, String.format("%.2f", bmi).replace(",", ".")))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplication4Theme {
        BMI()
    }
}