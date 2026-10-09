package com.mdi2.androidtestingsdgku

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.robolectric.shadows.ShadowSystemProperties.override

private class FakeControlledCatalogRepository : AsyncProductCatalogRepository {
    val result = CompletableDeferred<List<Product>>() // allow the test to decide when the result is available

    override suspend fun getProductCatalog(): List<Product> {
        return result.await()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class AsyncProductCatalogTests {
    @Test
    fun startIdle() = runTest {
        // create a test coroutine scope and dispatcher
        val fakeRepository = FakeControlledCatalogRepository()
        val service = AsyncProductCatalogService(fakeRepository, this)
        assertEquals(AsyncProductCatalogState.Idle, service.state.value)
    }

    @Test
    fun pendingRequest_showsLoadingState_ThenSuccess() = runTest {
        val fakeRepository = FakeControlledCatalogRepository()
        val service = AsyncProductCatalogService(fakeRepository, this)

        // starts loading but it could queued and not yet completed, so we need to check the loading state
        service.loadProductCatalog()

        // start the queued coroutine to run now, so we can check the loading state
        runCurrent()
        assertEquals(AsyncProductCatalogState.Loading, service.state.value)

        val products = listOf(Product("1", "Product 1",price=10.0), Product("2", "Product 2", price=20.0))
        fakeRepository.result.complete(products)

        // continue until all coroutines have completed, so the state should now be success
        advanceUntilIdle()

        // The state should now be success with the products
        assertEquals(AsyncProductCatalogState.Success(products), service.state.value)

    }
}