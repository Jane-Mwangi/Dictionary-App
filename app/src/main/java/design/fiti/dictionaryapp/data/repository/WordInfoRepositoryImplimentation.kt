package design.fiti.dictionaryapp.data.repository

import design.fiti.dictionaryapp.core.util.Resource
import design.fiti.dictionaryapp.data.local.WordInfoDao
import design.fiti.dictionaryapp.data.remote.dto.DictionaryApi
import design.fiti.dictionaryapp.domain.model.WordInfo
import design.fiti.dictionaryapp.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImplementation(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {
    override fun getWordIfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())
        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })

        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops,something went wrong!",
                data = wordInfos
            ))

        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server,please check your internet connection.",
                data = wordInfos
            ))
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos ))
    }
}