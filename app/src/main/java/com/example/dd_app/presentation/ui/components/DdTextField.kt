package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.dd_app.presentation.ui.theme.Colors

@Preview
@Composable
fun DdTextField(
    modifier: Modifier = Modifier,
    visibleText: Boolean = true,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    label: String? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean? = null,
    supportingText: String? = null,
    singleLine: Boolean = true,
    enabled: Boolean = true,
) {
    OutlinedTextField(
        enabled = enabled,
        singleLine = singleLine,
        visualTransformation = if (visibleText) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = {
            if (label != null) Text(text = label)
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedTextColor = Colors.Dark,
            focusedTextColor = Colors.Dark,
            disabledTextColor = Colors.Gray,
            focusedBorderColor = Colors.Purple,
            focusedLabelColor = Colors.Purple,
            errorBorderColor = Colors.Error,
            errorLabelColor = Colors.Error,
            unfocusedBorderColor = Colors.Unfocused,
            unfocusedLabelColor = Colors.Gray,
            disabledContainerColor = Colors.FilledComment,
            cursorColor = Colors.Purple
        ),
        trailingIcon = trailingIcon,
        isError = isError ?: false,
        supportingText = {
            if (isError == true && supportingText != null) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = supportingText,
                    color = Colors.Error,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        },
        textStyle = MaterialTheme.typography.bodyLarge,
    )
}