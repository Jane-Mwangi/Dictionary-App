package design.fiti.dictionaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import design.fiti.dictionaryapp.presentation.WordInfoItem
import design.fiti.dictionaryapp.presentation.WordInfoViewModel
import design.fiti.dictionaryapp.ui.theme.DictionaryAppTheme
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryAppTheme {
                val viewModel: WordInfoViewModel = hiltViewModel()
                val state = viewModel.state.value

                val snackbarHostState = remember { SnackbarHostState() }

                LaunchedEffect(key1 = true) {
                    viewModel.eventFlow.collectLatest { event ->
                        when (event) {
                            is WordInfoViewModel.UIEvent.ShowSnackbar -> {
                                snackbarHostState.showSnackbar(
                                    message = event.message
                                )

                            }
                        }

                    }

                }
                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                ) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .padding(paddingValues)
                            .background(color = MaterialTheme.colorScheme.background)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            TextField(
                                value = viewModel.searchQuery.value,
                                onValueChange = viewModel::onSearch,
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = {
                                    Text(text = "Search...")
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            LazyColumn(modifier = Modifier.fillMaxSize()) {

                                items(state.wordInfoItems.size) { i ->
                                    val wordInfo = state.wordInfoItems[i]
                                    if (i > 0) {
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                    WordInfoItem(wordInfo = wordInfo)

                                    if (i < state.wordInfoItems.size - 1) {
                                        Divider()
                                    }
                                }
                            }
                        }
                    }

                }


            }
        }
    }


}
