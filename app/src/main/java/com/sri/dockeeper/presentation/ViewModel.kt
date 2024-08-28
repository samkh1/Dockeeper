package com.sri.dockeeper.presentation

import com.sri.dockeeper.domain.repository.DocumentDaoRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ViewModel @Inject constructor(repository: DocumentDaoRepository) {


}