package com.sri.dockeeper.data.mapper

import com.sri.dockeeper.data.entities.DocumentEntity
import com.sri.dockeeper.domain.model.Document

class DocumentMapper {
    fun mapToDocument(docEntity: DocumentEntity): Document {
        return Document(
            title = docEntity.title,
            date = docEntity.date,
            image = docEntity.image
        )
    }

    fun mapToDocumentEntity(doc: Document): DocumentEntity {
        return DocumentEntity(
            title = doc.title,
            date = doc.date,
            image = doc.image
        )
    }
    fun mapListDocument(list: List<DocumentEntity>): List<Document> {
       return list.map { mapToDocument(it) }
    }
}
