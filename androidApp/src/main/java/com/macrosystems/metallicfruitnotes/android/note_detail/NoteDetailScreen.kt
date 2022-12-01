package com.macrosystems.metallicfruitnotes.android.note_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun NoteDetailScreen(
    noteId: Long,
    navController: NavController,
    viewModel: NoteDetailViewModel = hiltViewModel()

) {
    val state by viewModel.state.collectAsState()
    val hasNoteBeenChange by viewModel.hasNoteBeenSaved.collectAsState()

    LaunchedEffect(key1 = hasNoteBeenChange) {
        if (hasNoteBeenChange) {
           navController.popBackStack()
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = viewModel::saveNote,
                backgroundColor = Color.Black
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save note", tint = Color.White )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .background(Color(state.noteColor))
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TransparentHintTextField(
                text = state.noteTitle,
                hint = "Enter a title...",
                onValueChange = viewModel::onNoteTitleChanged,
                isHintVisible = state.isNoteTitleHintVisible,
                onFocusChanged = {
                    viewModel.onNoteTitleFocusedChanged(it.isFocused)
                },
                singleLine = true,
                textStyle = TextStyle(fontSize =  20.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = state.noteContent,
                hint = "Enter a content...",
                onValueChange = viewModel::onNoteContentChanged,
                isHintVisible = state.isNoteContentHintVisible,
                onFocusChanged = {
                    viewModel.onNoteContentFocusedChanged(it.isFocused)
                },
                singleLine = false,
                textStyle = TextStyle(fontSize =  20.sp),
                modifier = Modifier.weight(1f)
            )
        }

    }
}