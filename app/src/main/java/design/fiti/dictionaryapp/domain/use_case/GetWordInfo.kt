package design.fiti.dictionaryapp.domain.use_case

import design.fiti.dictionaryapp.core.util.Resource
import design.fiti.dictionaryapp.domain.model.WordInfo
import design.fiti.dictionaryapp.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val repository: WordInfoRepository

) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>>{
        if (word.isBlank()){
            return flow{}

        }
        return repository.getWordIfo(word)
    }
}
