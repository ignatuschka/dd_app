package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dd_app.R
import com.example.dd_app.presentation.ui.theme.Colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DdTwoButtonsDialog(
    modifier: Modifier = Modifier,
    onCloseDialog: () -> Unit = {},
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    BasicAlertDialog(onDismissRequest = onCloseDialog) {
        Card(
            modifier = modifier, shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Colors.White),
        ) {
            Column(modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.saveExerciseAlert),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium),
                    color = Colors.Dark,
                    textAlign = TextAlign.Center
                )
                Row(modifier = Modifier.padding(top = 24.dp)) {
                    DdTextButton(
                        modifier = Modifier.fillMaxWidth(fraction = 0.5f),
                        onClick = onDismiss,
                        text = stringResource(R.string.exit)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    DdButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onConfirm,
                        text = stringResource(R.string.save),
                    )
                }
            }
        }
    }
}