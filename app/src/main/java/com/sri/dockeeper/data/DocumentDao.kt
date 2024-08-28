package com.sri.dockeeper.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sri.dockeeper.data.entities.DocumentEntity

@Dao
interface DocumentDao {
    @Insert
    suspend fun insert(doc: DocumentEntity)

    @Query("SELECT * FROM DocumentEntity WHERE id = :docId")
    suspend fun getDocument(docId: Int): DocumentEntity

    @Query("SELECT * FROM DocumentEntity")
    suspend fun getAllDocs(): List<DocumentEntity>

    @Query("DELETE FROM DocumentEntity WHERE id = :id")
    suspend fun deleteDoc(id: Int)

}