package com.example.dd_app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dd_app.R
import com.example.dd_app.ui.components.DdAppBar
import com.example.dd_app.ui.components.DdButton
import com.example.dd_app.ui.components.DdTextField
import com.example.dd_app.ui.components.PolicyAndAgreementText
import com.example.dd_app.ui.components.RadioButtonGroup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavHostController) {
    val login = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }
    val passVisible = remember { mutableStateOf(false) }
    val repeatPass = remember { mutableStateOf("") }
    val repeatPassVisible = remember { mutableStateOf(false) }
    val radioOptions = listOf(stringResource(R.string.male),
        stringResource(R.string.female), stringResource(R.string.other)
    )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    val showErrors = remember { mutableStateOf(false) }
    val passError = remember { mutableStateOf(true) }
    val repeatPassError = remember { mutableStateOf(true) }
    val loginError = remember { mutableStateOf(true) }
    val nameError = remember { mutableStateOf(true) }
    Scaffold(
        containerColor = Color(255, 255, 255),
        topBar = {
            DdAppBar(navController = navController, title = stringResource(R.string.registration))
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(
                        PaddingValues(
                            16.dp,
                            padding.calculateTopPadding(),
                            16.dp,
                            padding.calculateBottomPadding()
                        )
                    )
            ) {
                DdTextField(
                    value = login.value,
                    visibleText = true,
                    onValueChange = { v -> login.value = v; loginError.value = v.isEmpty() },
                    label = stringResource(R.string.login),
                    trailingIcon = null,
                    isError = loginError.value && showErrors.value,
                    supportingText = stringResource(R.string.fillField),
                    onClick = null,
                )
                DdTextField(
                    value = name.value,
                    visibleText = true,
                    onValueChange = { v -> name.value = v; nameError.value = v.isEmpty() },
                    label = stringResource(R.string.nameOrNickname),
                    trailingIcon = null,
                    isError = nameError.value && showErrors.value,
                    supportingText = stringResource(R.string.fillField),
                    onClick = null,
                )
                DdTextField(
                    value = pass.value,
                    visibleText = passVisible.value,
                    onValueChange = { v -> pass.value = v; passError.value = v.isEmpty() },
                    label = stringResource(R.string.password),
                    trailingIcon = if (passVisible.value)
                        Icons.Filled.VisibilityOff
                    else Icons.Filled.Visibility,
                    onClick = { passVisible.value = !passVisible.value },
                    isError = passError.value && showErrors.value,
                    supportingText = stringResource(R.string.fillField),
                )
                DdTextField(
                    value = repeatPass.value,
                    visibleText = repeatPassVisible.value,
                    onValueChange = { v ->
                        repeatPass.value = v; repeatPassError.value = v.isEmpty() || pass.value != v
                    },
                    label = stringResource(R.string.repeatPassword),
                    trailingIcon = if (repeatPassVisible.value)
                        Icons.Filled.VisibilityOff
                    else Icons.Filled.Visibility,
                    onClick = {
                        repeatPassVisible.value = !repeatPassVisible.value
                    },
                    isError = repeatPassError.value && showErrors.value,
                    supportingText = stringResource(R.string.passwordsMustBeSame),
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = "Пол", fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Default,
                    lineHeight = 24.sp,
                    color = Color(16, 16, 16),
                )
                RadioButtonGroup(
                    selectedOption = selectedOption,
                    onClick = { text -> onOptionSelected(text) },
                    radioOptions = radioOptions
                )
                DdButton(
                    onClick = { showErrors.value = true }, text = stringResource(R.string.signUp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp, bottom = 24.dp),
                    contentPadding = PaddingValues(vertical = 12.dp)
                )
                PolicyAndAgreementText()
            }
        },
    )
}