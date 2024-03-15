package design.fiti.dictionaryapp.domain.model

import design.fiti.dictionaryapp.data.remote.dto.License

data class Phonetic(
    val audio: String,
    val license: License,
    val sourceUrl: String,
    val text: String
)


