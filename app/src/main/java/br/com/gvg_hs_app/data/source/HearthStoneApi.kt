package br.com.gvg_hs_app.data.source
import br.com.gvg_hs_app.data.domain.Card
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://omgvamp-hearthstone-v1.p.rapidapi.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface HearthStoneApiService {
    @Headers(
        "",
        ""
    )
    @GET("cards/sets/Goblins%20vs%20Gnomes?collectible=1")
    suspend fun getGvGCards(): List<SourceCard>
}


object HearthStoneApi {
    val retrofitService: HearthStoneApiService by lazy {
        retrofit.create(HearthStoneApiService::class.java)
    }
}