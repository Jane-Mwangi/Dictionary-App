package design.fiti.dictionaryapp.data.remote.dto

import design.fiti.dictionaryapp.data.local.entity.WordInfoEntity

data class WordInfoDto(
    val license: License,
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
){
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meanings=meanings.map { it.toMeaning() },
            phonetic=phonetic,
            word=word
        )
    }

}