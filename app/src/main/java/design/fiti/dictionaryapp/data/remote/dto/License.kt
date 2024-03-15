package design.fiti.dictionaryapp.data.remote.dto

data class License(
    val name: String,
    val url: String
) {
    fun toLicense():License {
        return License(
            name = name,
            url = url
        )
    }
}