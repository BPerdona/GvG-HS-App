package br.com.gvg_hs_app.network
import br.com.gvg_hs_app.data.Card
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://omgvamp-hearthstone-v1.p.rapidapi.com"

val okCreator = OkHttpCreator()

val client: OkHttpClient = okCreator.httpClient.build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface HearthStoneApiService {
    @Headers(
        "X-RapidAPI-Key: 8a50b37af0mshaac729cca51fe47p12c76ajsnbccbb1fe4915",
        "X-RapidAPI-Host: omgvamp-hearthstone-v1.p.rapidapi.com"
    )
    @GET("cards/sets/Goblins%20vs%20Gnomes?collectible=1")
    suspend fun getGvGCards(): List<Card>
}


object HearthStoneApi {
    val retrofitService: HearthStoneApiService by lazy {
        retrofit.create(HearthStoneApiService::class.java)
    }
}