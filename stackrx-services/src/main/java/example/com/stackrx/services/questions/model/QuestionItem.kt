package example.com.stackrx.services.questions.model

import com.squareup.moshi.Json

@Suppress("unused")
class QuestionItem(
        val tags: List<String>? = null,
        val owner: Owner? = null,
        @Json(name = "is_answered") val isAnswered: Boolean? = null,
        @Json(name = "view_count") val viewCount: Int? = null,
        @Json(name = "answer_count") val answerCount: Int? = null,
        val score: Int? = null,
        @Json(name = "lastActivity_date") val lastActivityDate: Int? = null,
        @Json(name = "creation_date") val creationDate: Int? = null,
        @Json(name = "question_id") val questionId: Int? = null,
        val link: String? = null,
        val title: String? = null,
        @Json(name = "accepted_answer_id") val acceptedAnswerId: Int? = null,
        @Json(name = "bounty_amount") val bountyAmount: Int? = null,
        @Json(name = "bounty_closes_date") val bountyClosesDate: Int? = null,
        @Json(name = "closed_date") val closedDate: Int? = null,
        @Json(name = "closed_reason") val closedReason: String? = null,
        @Json(name = "protected_date") val protectedDate: Int? = null,
        @Json(name = "community_owned_date") val communityOwnedDate: Int? = null
)
