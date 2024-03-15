package design.fiti.dictionaryapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import design.fiti.dictionaryapp.core.util.Resource
import design.fiti.dictionaryapp.domain.use_case.GetWordInfo
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfo: GetWordInfo

) : ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(WordInfoState())
    val state: State<WordInfoState> = _state


    //used to show one time event for example a snackbar
    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchQuery.value = query
        //cancel the current job
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            //call the usecase
            getWordInfo(query).onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value=state.value.copy(
                            wordInfoItems = result.data?: emptyList(),
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _state.value=state.value.copy(
                            wordInfoItems = result.data?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(UIEvent.ShowSnackbar(
                            result.message?:"Unknown Error"
                        ))
                    }

                    is Resource.Loading->{
                        _state.value=state.value.copy(
                            wordInfoItems = result.data?: emptyList(),
                            isLoading = true
                        )
                    }

                }
            }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }
}