package example.com.stackrx.services.questions.model

import com.squareup.moshi.Json

@Suppress("unused")
data class Owner(
        val reputation: Int = 0,
        @Json(name = "user_id") val userId: Int = 0,
        @Json(name = "user_type") val userType: String = "",
        @Json(name = "profile_image") val profileImage: String = "",
        @Json(name = "display_name") val displayName: String = "",
        val link: String = ""
)
