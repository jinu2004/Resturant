package di


import domain.database.NetworkService
import domain.viewmodel.MainViewModel
import domain.database.MongoDbSource
import domain.viewmodel.ManageDishesViewModel
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


val modules = module {
    single {
        KMongo.createClient("mongodb://localhost:27017").coroutine.getDatabase("caffe_manger")
    }
    single<NetworkService> { MongoDbSource(get()) }
    single { MainViewModel(get()) }
    single { ManageDishesViewModel(get()) }

}