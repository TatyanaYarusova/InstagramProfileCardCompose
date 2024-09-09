package com.example.instagramprofilecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.instagramprofilecard.presentation.MainViewModel
import com.example.instagramprofilecard.ui.InstagramProfileCard
import com.example.instagramprofilecard.ui.theme.InstagramProfileCardTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            RV(viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RV(viewModel: MainViewModel) {
    InstagramProfileCardTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            val models = viewModel.models.observeAsState(listOf())
            val lazyListState = rememberLazyListState()
            LazyColumn(
                state = lazyListState
            ) {
                items(models.value, key = { it.id }) { model ->
                    val dismissState = rememberSwipeToDismissBoxState()
                    if(dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
                        viewModel.delete(model)
                    }

                    SwipeToDismissBox(
                        enableDismissFromStartToEnd = false,
                        state = dismissState,
                        backgroundContent = {
                            Box(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxSize()
                                    .background(Color.Red.copy(alpha = 0.5f)),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Text(
                                    text = "Delete item",
                                    modifier = Modifier.padding(16.dp),
                                    color = Color.White,
                                    fontSize = 24.sp
                                )
                            }
                        }
                    ) {
                        InstagramProfileCard(
                            model = model,
                            onFollowedButtonClickListener = {
                                viewModel.changeFollowingStatus(model)
                            }
                        )
                    }
                }
            }
            val scope = rememberCoroutineScope()
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        lazyListState.scrollToItem(0)
                    }
                }
            ) {}
        }
    }
}




