package com.damian.examendamian.screens.others

import androidx.annotation.StringRes
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.damian.examendamian.R

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onContinueClicked: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var isButtonEnabled by rememberSaveable {
        mutableStateOf(email.isNotEmpty() && password.isNotEmpty())
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LogoImage()

        TextFieldEmail(
            onEmailChanged = { newEmail ->
                email = newEmail
                isButtonEnabled = newEmail.isNotEmpty() && password.isNotEmpty()
            }
        )
        TextFieldPassword(
            onPasswordChanged = { newPassword ->
                password = newPassword
                isButtonEnabled = email.isNotEmpty() && newPassword.isNotEmpty()
            }
        )

        ContinueButton(
            onClick = onContinueClicked,
            label = "Continuar",
            isEnabled = isButtonEnabled
        )
    }
}

@Composable
fun LogoImage() {
    Image(
        painter = painterResource(id = R.drawable.logoempresaandroid),
        contentDescription = null,
        modifier = Modifier
            .size(120.dp)
            .padding(bottom = 16.dp)
    )
}

@Composable
fun TextFieldEmail(onEmailChanged: (String) -> Unit) {
    var email by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = email,
        onValueChange = {
            email = it
            onEmailChanged(it)
        },
        label = { Text("Email") },
        placeholder = { Text(text = "Coloca tu email") },
        shape = RoundedCornerShape(percent = 20),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun TextFieldPassword(onPasswordChanged: (String) -> Unit){
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        value = password,
        onValueChange = {
            password = it
            onPasswordChanged(it)
        },
        label = { Text(text = "Contraseña") },
        placeholder = { Text(text = "Coloca tu contraseña") },
        shape = RoundedCornerShape(percent = 20),
        visualTransformation =
            if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            if (passwordVisible) {
                IconButton(onClick = { passwordVisible = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = "hide_password"
                    )
                }
            } else {
                IconButton(
                    onClick = { passwordVisible = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = "hide_password"
                    )
                }
            }
        }
    )
}

@Composable
fun ContinueButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
    isEnabled: Boolean = true
) {
    Button(
        modifier = modifier,
        onClick = { if (isEnabled) onClick() },
        shape = RoundedCornerShape(20.dp),
        enabled = isEnabled
    ) {
        val alpha = if (isEnabled) 1f else 0.5f
        Text(
            text = label,
            modifier = Modifier.alpha(alpha)
        )
    }
}