import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import domain.MongoDbSource
import domain.databaseInterface
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import screens.HomeScreen

@Composable
@Preview
fun App() {

    MaterialTheme {
        HomeScreen().View()
    }
}

fun main() = application {

    module {
        single { KMongo.createClient().coroutine.getDatabase("caffe_manger") }
        single<databaseInterface> { MongoDbSource(get()) }
    }

    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(
            placement = WindowPlacement.Floating, isMinimized = false, position = WindowPosition(
                Alignment.Center
            ), width = 1300.dp, height = 800.dp
        ), resizable = true
    ) {
        App()
    }
}



