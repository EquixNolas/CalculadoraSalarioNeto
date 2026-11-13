package com.example.calculadorasalario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CalculadoraSalario()
        }
    }
}

@Composable
fun CalculadoraSalario() {
    var mostrarResultados by remember { mutableStateOf(false) }

    var nombre by remember { mutableStateOf("") } //Recuerda el valor durante actualizacion de interfaz
    var edad by remember { mutableStateOf("") }
    var grupoProfesional by remember { mutableStateOf("") }
    var salario by remember { mutableStateOf("") } //"" guarda texto que puede cambiar
    var pagas by remember { mutableStateOf("12") }
    var discapacidad by remember { mutableStateOf("0") }
    var estadoCivil by remember { mutableStateOf("Soltero") }
    var hijos by remember { mutableStateOf("0") }
    var irpf by remember { mutableStateOf("15") }

    MaterialTheme{
        if (mostrarResultados){
            PantallaResultados(
                nombre = nombre,
                edad = edad,
                grupoProfesional = grupoProfesional,
                salario = salario.toDoubleOrNull() ?: 0.0,
                pagas = pagas.toIntOrNull() ?: 12,
                discapacidad = discapacidad,
                estadoCivil = estadoCivil,
                hijos = hijos.toIntOrNull() ?: 0,
                irpf = irpf.toDoubleOrNull() ?: 0.0,
                volver = { mostrarResultados = false }
            )
        } else {
            PantallaDatos(
                nombre = nombre,
                cambiarNombre = { nombre = it },
                edad = edad,
                cambiarEdad = {edad = it},
                grupoProfesional = grupoProfesional,
                cambiarGrupoProfesional = {grupoProfesional = it},
                salario = salario,
                cambiarSalario = { salario = it },
                pagas = pagas,
                cambiarPagas = { pagas = it },
                discapacidad = discapacidad,
                cambiarDiscapacidad = {discapacidad = it},
                estadoCivil = estadoCivil,
                cambiarEstadoCivil =  {estadoCivil = it},
                hijos = hijos,
                cambiarHijos = { hijos = it },
                irpf = irpf,
                cambiarIrpf = { irpf = it },
                calcular = { mostrarResultados = true }
            )
        }
    }
}
@Composable
fun PantallaDatos(
    nombre:String,
    cambiarNombre: (String) -> Unit,
    edad: String,
    cambiarEdad: (String) -> Unit,
    grupoProfesional: String,
    cambiarGrupoProfesional: (String) -> Unit,
    salario: String,
    cambiarSalario: (String) -> Unit,
    pagas: String,
    cambiarPagas: (String) -> Unit,
    discapacidad: String,
    cambiarDiscapacidad: (String) -> Unit,
    estadoCivil: String,
    cambiarEstadoCivil: (String) -> Unit,
    hijos: String,
    cambiarHijos: (String) -> Unit,
    irpf: String,
    cambiarIrpf:(String) -> Unit,
    calcular: () -> Unit
 ){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora de salario neto",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = cambiarNombre,
            label = { Text("Nombre del trabajador")},
            modifier = Modifier.fillMaxSize()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = edad,
            onValueChange = cambiarEdad,
            label = { Text("Edad") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = grupoProfesional,
            onValueChange = cambiarGrupoProfesional,
            label = { Text("Grupo profesional") },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = salario,
            onValueChange = cambiarSalario,
            label = {Text("Salario Bruto anual (€)")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = pagas,
            onValueChange = cambiarPagas,
            label = {Text("Número de pagas")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxSize()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = discapacidad,
            onValueChange = cambiarDiscapacidad,
            label = { Text("Grado de discapacidad (%)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = estadoCivil,
            onValueChange = cambiarEstadoCivil,
            label = { Text("Estado civil") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = hijos,
            onValueChange = cambiarHijos,
            label = { Text("Número de hijos")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxSize()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = irpf,
            onValueChange = cambiarIrpf,
            label = {Text("IRPF (%)")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxSize()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = calcular,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Calcular salario neto")
        }
    }
}

@Composable
fun PantallaResultados(
    nombre: String,
    edad: String,
    grupoProfesional: String,
    salario: Double,
    pagas: Int,
    discapacidad: String,
    estadoCivil: String,
    hijos: Int,
    irpf: Double,
    volver: () -> Unit
) {
    val retencion = salario * irpf / 100
    val deducciones = hijos * 100.0
    val salarioNeto = salario - retencion - deducciones

    val netoPorPaga = if (pagas > 0) {
        salarioNeto / pagas
    } else {
        0.0
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Text(
            text = "Resultado del cálculo",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Datos del Trabajador",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Text("Nombre: $nombre")
        Text("Edad: $edad años")
        Text("Grupo profesional: $grupoProfesional")
        Text("Estado civil: $estadoCivil")
        Text("Grado de discapacidad: $discapacidad %")
        Text("Número de hijos: $hijos")

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = "Resultados económicos",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Salario bruto anual: %.2f €".format(salario))
        Text("Número de pagas: $pagas")
        Text("Retención IRPF: %.2f €".format(retencion))
        Text("Deducciones: %.2f €".format(deducciones))

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = "Salario neto anual: %.2f €".format(salarioNeto),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Salario neto por paga: %.2f €".format(netoPorPaga)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = volver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}
