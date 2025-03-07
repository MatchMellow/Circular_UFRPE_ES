import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.circulapp.ui.UserSelectionScreen
import com.example.circulapp.ui.AlunoScreen
import com.example.circulapp.ui.MotoristaLoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    Log.d("MainActivity", "NavController criado!") // Verifique se o NavController foi criado

                    NavHost(navController = navController, startDestination = "userSelection") {

                        // Tela de Seleção de Usuário
                        composable("userSelection") {
                            Log.d("MainActivity", "Tela UserSelectionCarregada!") // Log para verificar se a tela foi chamada
                            UserSelectionScreen(
                                onAlunoSelected = { navController.navigate("alunoScreen") },
                                onMotoristaSelected = { navController.navigate("motoristaLogin") }
                            )
                        }

                        // Tela de Aluno
                        composable("alunoScreen") {
                            AlunoScreen(navController = navController)
                        }

                        // Tela de Login do Motorista
                        composable("motoristaLogin") {
                            MotoristaLoginScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UserSelectionScreen(onAlunoSelected: () -> Unit, onMotoristaSelected: () -> Unit) {
    Log.d("UserSelectionScreen", "Tela de Seleção Carregada!") // Log para verificar se o conteúdo está sendo renderizado

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Red) // Cor de fundo para testar
    ) {
        Button(onClick = onAlunoSelected, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Aluno")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onMotoristaSelected, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Motorista")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            UserSelectionScreen(
                onAlunoSelected = {},
                onMotoristaSelected = {}
            )
        }
    }
}