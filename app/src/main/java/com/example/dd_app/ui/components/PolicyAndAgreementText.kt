package com.example.dd_app.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun PolicyAndAgreementText() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color(16, 16, 16))) {
                append("Нажимая на кнопку, вы соглашаетесь\n c ")
            }
            withStyle(style = SpanStyle(color = Color(75, 9, 243))) {
                append("политикой конфиденциальности")
            }
            withStyle(style = SpanStyle(color = Color(16, 16, 16))) {
                append(" и обработки персональных\nданных, а также принимаете ")
            }
            withStyle(style = SpanStyle(color = Color(75, 9, 243))) {
                append("пользовательское соглашение")
            }
        },
        textAlign = TextAlign.Center,
        fontSize = 12.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        lineHeight = 18.sp,
    )
}