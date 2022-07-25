package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    var otpValue by remember { mutableStateOf("") }
                    val otpCount by remember { mutableStateOf(4) }
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        TopAppBar(
                            title = { Text(text = getString(R.string.underline_otpview_with_star_char), color = Color.White) },
                        )
                        Text(
                            modifier = Modifier.padding(
                                start = 0.dp,
                                top = 40.dp,
                                end = 0.dp,
                                bottom = 0.dp
                            ),
                            text = getString(R.string.title),
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue,
                        )
                        Text(
                            modifier = Modifier.padding(32.dp),
                            textAlign = TextAlign.Center,
                            text = getString(R.string.instruction_title),
                            color = Color.Blue,
                        )
                        OtpView(
                            otpText = otpValue,
                            onOtpTextChange = {
                                otpValue = it
                            },
                            type = OTP_VIEW_TYPE_UNDERLINE,
                            otpCount = otpCount,
                            password = true,
                            containerSize = 48.dp,
                            passwordChar = "*",
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            strokeColor = Color.Blue,
                            charColor = Color.Blue
                        )
                        Button(
                            onClick = {
                                Toast.makeText(this@MainActivity, getString(R.string.otp_correct), Toast.LENGTH_SHORT)
                                    .show()
                            },
                            modifier = Modifier.padding(32.dp),
                            enabled = otpValue.length == otpCount,
                        ) {
                            Text(text = getString(R.string.validate))
                        }
                    }
                }
            }
        }
    }
}
