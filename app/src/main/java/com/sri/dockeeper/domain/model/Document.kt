package com.sri.dockeeper.domain.model

import java.util.Date

data class Document(
    val id: Int,
    var title: String,
    var date: Date,
)
