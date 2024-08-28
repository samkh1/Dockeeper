package com.sri.dockeeper.data.mapper

import com.sri.dockeeper.data.entities.DocumentEntity
import com.sri.dockeeper.domain.model.Document

class DocumentMapper {
    fun mapToDocument(docEntity: DocumentEntity): Document {
        return Document(
            id = docEntity.id,
            title = docEntity.title,
            date = docEntity.date,
        )
    }

    fun mapListDocument(list: List<DocumentEntity>): List<Document> {
       return list.map { mapToDocument(it) }
    }
}
