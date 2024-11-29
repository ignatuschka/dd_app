package com.example.dd_app.presentation.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.dd_app.R

@Preview
@Composable
fun PolicyAndAgreementText() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color(16, 16, 16))) {
                append(stringResource(R.string.policyAndAgreementText1))
            }
            withStyle(style = SpanStyle(color = Color(75, 9, 243))) {
                append(stringResource(R.string.policyAndAgreementText2))
            }
            withStyle(style = SpanStyle(color = Color(16, 16, 16))) {
                append(stringResource(R.string.policyAndAgreementText3))
            }
            withStyle(style = SpanStyle(color = Color(75, 9, 243))) {
                append(stringResource(R.string.policyAndAgreementText4))
            }
        },
        textAlign = TextAlign.Center,
        fontSize = 12.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        lineHeight = 18.sp,
    )
}