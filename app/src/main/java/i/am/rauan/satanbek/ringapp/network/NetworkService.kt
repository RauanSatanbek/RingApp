package i.am.rauan.satanbek.ringapp.network

import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET
import java.util.*


interface NetworkService {

    @GET("/api/ping")
    fun ping(): Call<Ping>

    /**
     * Factory class for convenient creation of the Api Service interface
     */
    object Factory {

        fun create(): NetworkService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.ringapp.me")
                .build()

            return retrofit.create(NetworkService::class.java)
        }
    }
}