package com.example.wiprosystemtask.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.wiprosystemtask.R
import com.example.wiprosystemtask.base.BaseInterface
import com.example.wiprosystemtask.repo.factsResponse.FactsResponse
import com.example.wiprosystemtask.repo.factsResponse.RowsItem
import com.example.wiprosystemtask.repository.FactsRepository
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21, 22])
class FactsViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val factsRepository: FactsRepository = mock()
    private lateinit var viewModel: FactsViewModel

    var factsResponseD: FactsResponse? = null

    private val teams = listOf(
        RowsItem(
            "http://3.bp.blogspot.com/__mokxbTmuJM/RnWuJ6cE9cI/AAAAAAAAATw/6z3m3w9JDiU/s400/019843_31.jpg",
            "Lakers",
            "Language"
        ),
        RowsItem(
            "http://3.bp.blogspot.com/__mokxbTmuJM/RnWuJ6cE9cI/AAAAAAAAATw/6z3m3w9JDiU/s400/019843_31.jpg",
            "Celtics",
            "Mounties"
        )
    )

    private var factsResponseO = FactsResponse("About", teams)


    @Before
    fun setUp() {
        viewModel = FactsViewModel(factsRepository)
    }

    @Test
    fun sendSuccessToUI() {
        // Given
        val responseListenerArgumentCaptor =
            argumentCaptor<BaseInterface.IResponseListener<FactsResponse>>()
        val observer: Observer<FactsUIModel> = mock()
        viewModel.commonViewStateLiveData.observeForever(observer)

        // When -> Execute SendOtp
        if (factsResponseD!=null){
            viewModel.fetchFacts()
        }else{
            this.factsResponseD
        }

        // Then -> Check Interface method invocation
        Mockito.verify(factsRepository).getFacts(
            responseListenerArgumentCaptor.capture()
        )
        this.factsResponseD = factsResponseO

        // When -> OnProgress
        responseListenerArgumentCaptor.firstValue.onProgress()

        verify(observer, times(1)).onChanged(FactsUIModel.ShowProgress(false))

        // Then -> Check LiveData State
        Mockito.verify(observer).onChanged(FactsUIModel.ShowProgress(true))

        // When -> Send Success Resp
        responseListenerArgumentCaptor.firstValue.onSuccess(mock())

        // Then -> Check LiveData State
        Mockito.verify(observer).onChanged(FactsUIModel.ShowProgress(false))
        Mockito.verify(observer).onChanged(FactsUIModel.FactsData(factsResponseD))
    }


    @Test
    fun sendFailureToUI() {
        // Given
        val responseListenerArgumentCaptor =
            argumentCaptor<BaseInterface.IResponseListener<FactsResponse>>()
        val observer: Observer<FactsUIModel> = mock()

        viewModel.commonViewStateLiveData.observeForever(observer)

        // When -> Execute SendOtp
        if (factsResponseD!=null){
            viewModel.fetchFacts()
        }else{
            this.factsResponseD
        }


        // Then -> Check Interface method invocation
        Mockito.verify(factsRepository).getFacts(
            responseListenerArgumentCaptor.capture()
        )
        this.factsResponseD = factsResponseO

        // When -> OnProgress
        responseListenerArgumentCaptor.firstValue.onProgress()


        // Then -> Check LiveData State
        Mockito.verify(observer).onChanged(FactsUIModel.ShowProgress(true))

        // When -> Send Success Resp
        responseListenerArgumentCaptor.firstValue.onFailure(mock())

        // Then -> Check LiveData State
        Mockito.verify(observer).onChanged(FactsUIModel.ShowProgress(false))
        Mockito.verify(observer).onChanged(FactsUIModel.ShowError("Error"))

    }


}