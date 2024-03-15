package design.fiti.dictionaryapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import design.fiti.dictionaryapp.data.remote.dto.License
import design.fiti.dictionaryapp.data.remote.dto.PhoneticDto
import design.fiti.dictionaryapp.domain.model.Meaning
import design.fiti.dictionaryapp.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val meanings: List<Meaning>,
    val phonetic: String,
    val word: String,
    @PrimaryKey val id: Int? = null
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings = meanings,
            phonetic = phonetic,
            word = word,
        )
    }
}
