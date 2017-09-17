package example.com.stackrx.services.questions.model

import com.squareup.moshi.Json

@Suppress("unused")
data class Owner(
        val reputation: Int? = null,
        @Json(name = "user_id") val userId: Int? = null,
        @Json(name = "user_type") val userType: String? = null,
        @Json(name = "profile_image") val profileImage: String? = null,
        @Json(name = "display_name") val displayName: String? = null,
        val link: String? = null
)
