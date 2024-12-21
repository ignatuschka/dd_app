package com.example.dd_app.presentation.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.dd_app.R
import com.example.dd_app.presentation.ui.theme.Colors

@Preview
@Composable
fun PolicyAndAgreementText() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Colors.Dark)) {
                append(stringResource(R.string.policyAndAgreementText1))
            }
            withStyle(style = SpanStyle(color = Colors.Purple)) {
                append(stringResource(R.string.policyAndAgreementText2))
            }
            withStyle(style = SpanStyle(color = Colors.Dark)) {
                append(stringResource(R.string.policyAndAgreementText3))
            }
            withStyle(style = SpanStyle(color = Colors.Purple)) {
                append(stringResource(R.string.policyAndAgreementText4))
            }
        },
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodySmall,
    )
}