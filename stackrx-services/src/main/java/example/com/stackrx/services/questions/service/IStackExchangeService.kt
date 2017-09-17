package example.com.stackrx.services.questions.service

import com.squareup.moshi.Moshi
import example.com.stackrx.services.questions.model.Questions
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val ENDPOINT = "https://api.stackexchange.com"

/**
 * Retrofit service interface to the stack exchange API
 *
 * {@see [API](https://api.stackexchange.com/docs)
 */
internal interface IStackExchangeService {

    /**
     * Gets all the questions on the site.
     *
     * {@see [API](https://api.stackexchange.com/docs/questions) }
     */
    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("/2.2/questions")
    fun questionsInternal(@Query("order") order: String,
                          @Query("sort") sort: String,
                          @Query("format") format: String = "json",
                          @Query("site") site: String = "stackoverflow"): Observable<Questions>
}

class StackExchangeService(client: OkHttpClient, moshi: Moshi) {

    private val service: IStackExchangeService = Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
            .create(IStackExchangeService::class.java)

    @JvmOverloads fun questions(order: String = "desc", sort: String = "activity"): Observable<Questions> =
            service.questionsInternal(order = order, sort = sort)
}


