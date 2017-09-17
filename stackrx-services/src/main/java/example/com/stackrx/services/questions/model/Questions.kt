package example.com.stackrx.services.questions.model

import com.squareup.moshi.Json

@Suppress("unused")
class Questions(
        val items: List<QuestionItem>? = null,
        @Json(name = "has_more") val hasMore: Boolean? = null,
        @Json(name = "quota_max") val quotaMax: Int? = null,
        @Json(name = "quota_remaining") val quotaRemaining: Int? = null
)
