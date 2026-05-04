package com.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.AppTheme

data class FormData(
    val nombre: String = "",
    val correo: String = "",
    val edad: String = "",
    val mensaje: String = ""
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    var showResult by remember { mutableStateOf(false) }

    // Estado principal que conserva la información enviada entre pantallas.
    var formData by remember {
        mutableStateOf(FormData())
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (showResult) {
            PantallaResultado(
                formData = formData,
                onRegresar = { showResult = false },
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            PantallaFormulario(
                formData = formData,
                onFormDataChange = { formData = it },
                onEnviar = { showResult = true },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun PantallaFormulario(
    formData: FormData,
    onFormDataChange: (FormData) -> Unit,
    onEnviar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Formulario de datos",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Captura del nombre en el formulario.
        Text(text = "Nombre")
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = formData.nombre,
            onValueChange = { onFormDataChange(formData.copy(nombre = it)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Captura del correo en el formulario.
        Text(text = "Correo")
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = formData.correo,
            onValueChange = { onFormDataChange(formData.copy(correo = it)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Captura de la edad en el formulario.
        Text(text = "Edad")
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = formData.edad,
            onValueChange = { onFormDataChange(formData.copy(edad = it)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Captura del mensaje en el formulario.
        Text(text = "Mensaje")
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = formData.mensaje,
            onValueChange = { onFormDataChange(formData.copy(mensaje = it)) },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onEnviar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar información")
        }
    }
}

@Composable
fun PantallaResultado(
    formData: FormData,
    onRegresar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Información recibida",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Aquí se muestra la información recibida desde la pantalla de formulario.
        Text(text = "Nombre: ${formData.nombre}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Correo: ${formData.correo}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Edad: ${formData.edad}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Mensaje: ${formData.mensaje}")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onRegresar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Regresar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    AppTheme {
        AppContent()
    }
}
