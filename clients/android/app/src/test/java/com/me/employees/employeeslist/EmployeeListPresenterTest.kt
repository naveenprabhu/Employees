package com.me.employees.employeeslist

import com.me.employees.model.Employee
import com.me.employees.service.EmployeeService
import com.nhaarman.mockitokotlin2.then
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class EmployeeListPresenterTest {

    @Mock
    lateinit var employeeService: EmployeeService

    @Mock
    lateinit var employeeListView: EmployeeListView

    lateinit var presenter: EmployeeListPresenter

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        presenter = EmployeeListPresenter(employeeService)
        presenter.setView(employeeListView)
    }

    @Test
    fun shouldMakeNetworkCallToFetchEmployees() {
        var employeeList = listOf<Employee>(
            Employee()
        )
        whenever(employeeService.getEmployees()).thenReturn(Observable.just(employeeList))

        presenter.getEmployees()
        verify(employeeService).getEmployees()
    }


    @Test
    fun shouldReturnSuccessfulResponseandUpdateTheAdapter() {

        var expectedEmployeeList = listOf<Employee>(
            Employee()
        )
        whenever(employeeService.getEmployees()).thenReturn(Observable.just(expectedEmployeeList))
        presenter.getEmployees()

        then(verify(presenter.getView()).updateAdapter())
        then(verify(presenter.getView()).dismissSwipeRefresh())
        val actualEmployeeList = presenter.employeeList
        then(assertThat(actualEmployeeList, `is`(expectedEmployeeList)))
    }


    @Test
    fun shouldResponseFailAndDisplayError() {

        var expectedEmployeeList = listOf<Employee>(
            Employee()
        )

        whenever(employeeService.getEmployees()).thenReturn(Observable.error(IOException("Something went wrong")))

                presenter.getEmployees()

        then(verify(presenter.getView()).displayError())
        then(verify(presenter.getView()).dismissSwipeRefresh())
    }





}