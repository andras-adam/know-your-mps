package eu.neoaren.knowyourmps

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://users.metropolia.fi/"

private val moshi = Moshi.Builder()
  .add(KotlinJsonAdapterFactory())
  .build()

private val retrofit = Retrofit.Builder()
  .addConverterFactory(MoshiConverterFactory.create(moshi))
  .baseUrl(BASE_URL)
  .build()

interface MemberOfParliamentApiService {
  @GET("/~peterh/mps.json")
  suspend fun getMPs(): List<MemberOfParliament>
}

object MemberOfParliamentApi {

  val service : MemberOfParliamentApiService by lazy {
    retrofit.create(MemberOfParliamentApiService::class.java)
  }

}
