package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun DdTextField(
    visibleText: Boolean = true,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    label: String? = null,
    trailingIcon: ImageVector? = null,
    isError: Boolean? = null,
    supportingText: String? = null,
    onClick: (() -> Unit)? = null
) {
    OutlinedTextField(
        visualTransformation = if (visibleText) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { if (label != null) Text(text = label) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(
                75,
                9,
                243
            ),
            focusedLabelColor = Color(75, 9, 243),
            errorBorderColor = Color(167, 0, 1),
            errorLabelColor = Color(167, 0, 1),
            unfocusedBorderColor = Color(205, 207, 212),
            unfocusedLabelColor = Color(128, 128, 128),
        ),
        trailingIcon = {
            if (onClick != null && trailingIcon != null) IconButton(onClick = { onClick() }) {
                Icon(imageVector = trailingIcon, null)
            }
        },
        isError = isError ?: false,
        supportingText = {
            if (isError == true && supportingText != null) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = supportingText,
                    color = Color(167, 0, 1),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.Default,
                    lineHeight = 16.2.sp,
                )
            }
        },
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Default,
            lineHeight = 24.sp,
        )
    )
}