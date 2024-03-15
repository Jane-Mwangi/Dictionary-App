package design.fiti.dictionaryapp.data.remote.dto

import design.fiti.dictionaryapp.domain.model.Phonetic

data class PhoneticDto(
    val audio: String,
    val license: License,
    val sourceUrl: String,
    val text: String
) {
    fun toPhonetic(): Phonetic {
        return Phonetic(
            audio = audio,
            license = license,
            sourceUrl = sourceUrl,
            text = text
        )
    }
}