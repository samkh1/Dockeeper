package com.sri.dockeeper.data.repository

import com.sri.dockeeper.data.DocumentDao
import com.sri.dockeeper.data.entities.DocumentEntity
import com.sri.dockeeper.data.mapper.DocumentMapper
import com.sri.dockeeper.di.AppDataBase
import com.sri.dockeeper.domain.model.Document
import com.sri.dockeeper.domain.repository.DocumentDaoRepository
import javax.inject.Inject

class DocumentDaoRepository_Imp @Inject constructor(
    private val docDao: DocumentDao,
    private val mapper: DocumentMapper,
) : DocumentDaoRepository {

    override suspend fun getDocument(id: Int): Document {
        return mapper.mapToDocument(docDao.getDocument(id))
    }

    override suspend fun getAllDocument(): List<Document> {
        return mapper.mapListDocument(docDao.getAllDocs())
    }

    override suspend fun deleteDocument(id: Int) {
        docDao.deleteDoc(id)
    }

    override suspend fun insertDocument(doc: DocumentEntity) {
        docDao.insert(doc)
    }
}
