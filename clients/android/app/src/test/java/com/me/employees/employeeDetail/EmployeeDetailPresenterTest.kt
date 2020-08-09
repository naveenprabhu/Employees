package com.me.employees.employeeDetail

import com.me.employees.employeedetail.EmployeeDetailPresenter
import com.me.employees.employeedetail.EmployeeDetailView
import com.me.employees.model.Employee
import com.me.employees.service.EmployeeService
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.then
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class EmployeeDetailPresenterTest {

    @Mock
    lateinit var employeeService: EmployeeService


    lateinit var presenter: EmployeeDetailPresenter

    @Mock
    lateinit var employeeDetailView: EmployeeDetailView

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        presenter = EmployeeDetailPresenter(employeeService)
        presenter.setView(employeeDetailView)
    }


    @Test
    fun shouldMakeNetworkCallToFetchEmployees() {
        var employee = Employee()

        whenever(employeeService.getEmployeeDetail(1)).thenReturn(Observable.just(employee))

        presenter.getEmployeeDetail(1)
        verify(employeeService).getEmployeeDetail(1)
    }


    @Test
    fun shouldReturnSuccessfulResponseandUpdateTheAdapter() {

        val base64FullImageString = "fullImageString"
        val employeeId = 1
        var employee = Employee(
            employeeId,"John","David","M", "13 Jan 2001", "baseString",
            base64FullImageString
        )

        whenever(employeeService.getEmployeeDetail(employeeId)).thenReturn(Observable.just(employee))
        presenter.getEmployeeDetail(employeeId)

        var captor = argumentCaptor<String>()

        then(verify(presenter.getView()).updateImage(captor.capture()))

        then(Assert.assertThat(captor.firstValue, CoreMatchers.`is`(base64FullImageString)))
    }


    @Test
    fun shouldResponseFailAndDisplayError() {

        val employeeId = 1
        var employee = Employee(
            employeeId,"John","David","M", "13 Jan 2001", "baseString",
            "base64FullImageString"
        )

        whenever(employeeService.getEmployeeDetail(employeeId)).thenReturn(Observable.error(IOException("Something went wrong")))

        presenter.getEmployeeDetail(employeeId)

        then(verify(presenter.getView()).displayError())
    }
}