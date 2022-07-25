package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val OTP_VIEW_TYPE_NONE = 0
const val OTP_VIEW_TYPE_UNDERLINE = 1
const val OTP_VIEW_TYPE_BOX = 2

/**
 * create the otpview.
 */
@Preview
@Composable
fun OtpView(
    modifier: Modifier = Modifier,
    otpText: String = "",
    charColor: Color = Color.Black,
    charBackground: Color = Color.Transparent,
    charSize: TextUnit = 16.sp,
    strokeColor: Color = Color.Black,
    containerSize: Dp = charSize.value.dp * 2,
    otpCount: Int = 4,
    type: Int = OTP_VIEW_TYPE_UNDERLINE,
    enabled: Boolean = true,
    password: Boolean = false,
    passwordChar: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    onOtpTextChange: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = otpText,
        onValueChange = {
            if (it.length <= otpCount) {
                onOtpTextChange.invoke(it)
            }
        },
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        decorationBox = {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                repeat(otpCount) { index ->
                    Spacer(modifier = Modifier.width(2.dp))
                    CharView(
                        index = index,
                        text = otpText,
                        charColor = charColor,
                        strokeColor = strokeColor,
                        charSize = charSize,
                        containerSize = containerSize,
                        type = type,
                        charBackground = charBackground,
                        password = password,
                        passwordChar = passwordChar,
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                }
            }
        })
}

/**
 * Set the view of otp character.
 */
@Composable
private fun CharView(
    index: Int,
    text: String,
    charColor: Color,
    strokeColor: Color,
    charSize: TextUnit,
    containerSize: Dp,
    type: Int = OTP_VIEW_TYPE_UNDERLINE,
    charBackground: Color = Color.Transparent,
    password: Boolean = false,
    passwordChar: String = ""
) {
    val modifier = if (type == OTP_VIEW_TYPE_BOX) {
        Modifier
            .size(containerSize)
            .border(
                width = 1.dp,
                color = strokeColor,
                shape = MaterialTheme.shapes.medium
            )
            .padding(bottom = 4.dp)
            .background(charBackground)
    } else Modifier
        .width(containerSize)
        .background(charBackground)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val char = when {
            index >= text.length -> ""
            password -> passwordChar
            else -> text[index].toString()
        }
        Text(
            text = char,
            color = charColor,
            modifier = modifier.wrapContentHeight(),
            style = MaterialTheme.typography.body1,
            fontSize = charSize,
            textAlign = TextAlign.Center,
        )
        if (type == OTP_VIEW_TYPE_UNDERLINE) {
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .background(strokeColor)
                    .height(1.dp)
                    .width(containerSize)
            )
        }
    }
}

