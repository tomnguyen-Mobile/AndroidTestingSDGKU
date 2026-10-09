package com.mdi2.androidtestingsdgku

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

interface AsyncProductCatalogRepository {
    // run in another thread
    suspend fun getProductCatalog(): List<Product>
}

sealed interface AsyncProductCatalogState{
    data object Idle : AsyncProductCatalogState
    data object Loading : AsyncProductCatalogState
    data class Success(val products: List<Product>) : AsyncProductCatalogState
    data class Error(val message: String) : AsyncProductCatalogState
}

class AsyncProductCatalogService(private val repository: AsyncProductCatalogRepository,
                                 private val scope: CoroutineScope){
    private val mutableState = MutableStateFlow<AsyncProductCatalogState>(AsyncProductCatalogState.Idle)
    val state: StateFlow<AsyncProductCatalogState> = mutableState.asStateFlow()
    private var activeLoad: Job? = null
    fun loadProductCatalog(){
        activeLoad?.cancel()
        activeLoad = scope.launch {
            mutableState.value = AsyncProductCatalogState.Loading
            try {
                val products = repository.getProductCatalog()
                mutableState.value = AsyncProductCatalogState.Success(products)
            } catch (e: Exception){
                mutableState.value = AsyncProductCatalogState.Error(e.message ?: "Unknown error")
            }
        }
    }
}