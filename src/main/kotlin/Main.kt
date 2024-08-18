import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sun.tools.javac.Main
import domain.viewmodel.MainViewModel
import domain.viewmodel.ManageDishesViewModel
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.context.startKoin
import org.koin.logger.slf4jLogger
import screens.HomeScreen

@Composable
@Preview
fun App() {

    MaterialTheme {
        KoinContext {
            HomeScreen().View()
            koinViewModel<ManageDishesViewModel>()
            koinViewModel<MainViewModel>()
        }

    }
}

fun main() = application {
    startKoin {
        modules(di.modules)
        slf4jLogger()
    }
    Window(
        onCloseRequest = ::exitApplication,
        resizable = true
    ) {
        App()
    }
}



