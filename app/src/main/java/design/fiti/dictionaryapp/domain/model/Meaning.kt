package design.fiti.dictionaryapp.domain.model

import design.fiti.dictionaryapp.data.remote.dto.DefinitionDto

data class Meaning(
    val antonyms: List<String>,
    val definitions: List<Definition>,
    val partOfSpeech: String,
    val synonyms: List<String>
)
