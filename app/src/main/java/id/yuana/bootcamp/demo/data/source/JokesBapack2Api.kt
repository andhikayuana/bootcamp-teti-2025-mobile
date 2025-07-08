package id.yuana.bootcamp.demo.data.source

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import id.yuana.bootcamp.demo.data.source.response.GetRandomResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET

interface JokesBapack2Api {

    @GET("v1/text/random")
    suspend fun randomJoke(): GetRandomResponse

    companion object {
        const val BASE_URL = "https://jokes-bapack2-api.yuana.id/"

        fun createHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        }

        fun create(
            baseUrl: String = BASE_URL,
            httpClient: OkHttpClient = createHttpClient()
        ): JokesBapack2Api {
            val retrofit = Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(
                    Json.asConverterFactory(
                        "application/json; charset=UTF8".toMediaType()
                    )
                )
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(JokesBapack2Api::class.java)
        }
    }
}