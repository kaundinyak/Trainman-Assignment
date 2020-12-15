package com.kaundinyakasibhatla.trainman_assignment.data.model

data class Gif (
    val images: Image?

)

data class Image(
    val original : Original
)

data class Original(
    val url : String?,
)