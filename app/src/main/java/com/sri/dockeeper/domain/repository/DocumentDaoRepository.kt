package com.sri.dockeeper.domain.repository

import com.sri.dockeeper.data.entities.DocumentEntity
import com.sri.dockeeper.domain.model.Document

interface DocumentDaoRepository {
    suspend fun getDocument(id: Int): Document
    suspend fun getAllDocument(): List<Document>
    suspend fun deleteDocument(id: Int)
    suspend fun insertDocument(doc: DocumentEntity)
}