package example.com.stackrx.services.questions.model

import com.squareup.moshi.Json

@Suppress("unused")
class Questions(
        val items: List<QuestionItem> = emptyList(),
        @Json(name = "has_more") val hasMore: Boolean = false,
        @Json(name = "quota_max") val quotaMax: Int = 0,
        @Json(name = "quota_remaining") val quotaRemaining: Int = 0
)
