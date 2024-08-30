package com.sri.dockeeper.domain

import com.sri.dockeeper.domain.model.Document

data class DocState(
    val listDoc: List<Document>? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)