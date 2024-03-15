package design.fiti.dictionaryapp.domain.repository

import design.fiti.dictionaryapp.core.util.Resource
import design.fiti.dictionaryapp.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {


    fun getWordIfo(word:String): Flow<Resource<List<WordInfo>>>
}