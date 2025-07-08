package id.yuana.bootcamp.demo.data.source

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import id.yuana.bootcamp.demo.data.model.Product
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET

interface ProductApi {

    @GET("objects")
    suspend fun getObjects(): List<Product>

    //TODO: implement

    companion object {
        const val BASE_URL = "https://api.restful-api.dev/"

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
        ): ProductApi {
            val retrofit = Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(
                    Json.asConverterFactory(
                        "application/json; charset=UTF8".toMediaType()
                    )
                )
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(ProductApi::class.java)
        }
    }
}

object Module {
    val productApi: ProductApi by lazy {
        ProductApi.create()
    }
}