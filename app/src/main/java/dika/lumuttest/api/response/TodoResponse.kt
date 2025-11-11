package dika.lumuttest.api.response

import com.google.gson.annotations.SerializedName


data class TodoResponse (
    @SerializedName("userId") val userId: Int? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("title") val title: String?= null,
    @SerializedName("completed") val completed: Boolean? = null
)