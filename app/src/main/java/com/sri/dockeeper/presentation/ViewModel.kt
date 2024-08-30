package com.sri.dockeeper.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sri.dockeeper.domain.DocState
import com.sri.dockeeper.domain.model.Document
import com.sri.dockeeper.domain.repository.DocumentDaoRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val repository: DocumentDaoRepository) : ViewModel() {
    var state by mutableStateOf(DocState())
    private set

     fun insertDoc(doc: Document) {
        viewModelScope.launch {
            repository.insertDocument(doc)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    suspend fun getAllDoc(): List<Document>? {
        var listDoc: List<Document>? = null
        viewModelScope.launch {
        state = state.copy(listDoc = null, isLoading = true, isError = false)
            try {
                listDoc = repository.getAllDocument()
                state = state.copy(listDoc = listDoc, isLoading = false, isError = false)
            } catch (e: Exception) {
                state = state.copy(listDoc = null, isError = true, isLoading = false)
            }
        }
        return listDoc
    }
}
