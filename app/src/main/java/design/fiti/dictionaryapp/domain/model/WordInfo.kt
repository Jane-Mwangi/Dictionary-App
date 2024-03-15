package design.fiti.dictionaryapp.domain.model

import design.fiti.dictionaryapp.data.remote.dto.License
import design.fiti.dictionaryapp.data.remote.dto.PhoneticDto

data class WordInfo(
    val meanings: List<Meaning>,
    val phonetic: String,
    val word: String
)
