package example.com.stackrx.services.questions.model

import com.squareup.moshi.Json

@Suppress("unused")
class QuestionItem(
        val tags: List<String> = emptyList(),
        val owner: Owner = Owner(),
        @Json(name = "is_answered") val isAnswered: Boolean = false,
        @Json(name = "view_count") val viewCount: Int = 0,
        @Json(name = "answer_count") val answerCount: Int = 0,
        val score: Int = 0,
        @Json(name = "lastActivity_date") val lastActivityDate: Int = 0,
        @Json(name = "creation_date") val creationDate: Int = 0,
        @Json(name = "question_id") val questionId: Int = 0,
        val link: String = "",
        val title: String = "",
        @Json(name = "accepted_answer_id") val acceptedAnswerId: Int = 0,
        @Json(name = "bounty_amount") val bountyAmount: Int = 0,
        @Json(name = "bounty_closes_date") val bountyClosesDate: Int = 0,
        @Json(name = "closed_date") val closedDate: Int = 0,
        @Json(name = "closed_reason") val closedReason: String = "",
        @Json(name = "protected_date") val protectedDate: Int = 0,
        @Json(name = "community_owned_date") val communityOwnedDate: Int = 0
)
