package com.example.wiprosystemtask.fragment

import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider
import com.airtel.xlabs.retailer.sl.utils.ViewModelUtil
import com.example.wiprosystemtask.R
import com.example.wiprosystemtask.adapter.FactsAdapter
import com.example.wiprosystemtask.di.component.DaggerApplicationComponent
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
//checking unit test cases in different sdk
@Config(sdk = [21, 22])
class FactsFragmentTest {

    private lateinit var fragmentFactory: FragmentFactory

    private lateinit var fragmentScenario: FragmentScenario<FactsFragment>
    private val viewModel: FactsViewModel = mock()
    private val commonViewStateMutableLiveData: MutableLiveData<FactsUIModel> = MutableLiveData()

    var mockAdapter: FactsAdapter = mock()


    @Before
    fun setup() {
        Mockito.`when`(viewModel.commonViewStateLiveData).thenReturn(commonViewStateMutableLiveData)
        val appComponent =
            DaggerApplicationComponent.builder().create(ApplicationProvider.getApplicationContext())
                .build()
        this.fragmentFactory = appComponent.fragmentFactory()

        fragmentScenario = launchFragmentInContainer(
            themeResId = R.style.AppTheme,
            instantiate = {
                FactsFragment.newInstance().apply {
                    viewModelFactory = ViewModelUtil.createFor(viewModel)
                }
            })
    }


    @Test
    @Throws(Exception::class)
    fun onViewCreated() {

        if (viewModel.factsResponse != null) {
            Mockito.verify(viewModel).fetchFacts()
        } else {
        }
    }


}