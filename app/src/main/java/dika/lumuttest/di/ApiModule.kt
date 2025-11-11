package dika.lumuttest.di

import dika.lumuttest.api.Client
import dika.lumuttest.api.endpoint.LumutEndPoint
import org.koin.dsl.module
import retrofit2.Retrofit

object ApiModule {

    fun get() = module {
        single<Retrofit> { Client.provideClient() }
        single<LumutEndPoint> { get<Retrofit>().create(LumutEndPoint::class.java) }
    }

}