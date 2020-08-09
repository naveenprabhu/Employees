package com.me.employees.employeeslist

import com.me.employees.base.BasePreseneter
import com.me.employees.di.ActivityScope
import com.me.employees.model.Employee
import com.me.employees.service.EmployeeService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


@ActivityScope
class EmployeeListPresenter @Inject constructor(employeeService: EmployeeService) : BasePreseneter<EmployeeListView>() {

    private val employeeService: EmployeeService = employeeService
    var employeeList: List<Employee>? = null

    fun getEmployees() {
         employeeService.getEmployees()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<List<Employee>>(){
                override fun onComplete() {
                    getView().dismissSwipeRefresh()
                }

                override fun onNext(employees: List<Employee>?) {
                    employeeList = employees
                    getView().updateAdapter()
                }

                override fun onError(e: Throwable?) {
                    getView().displayError()
                    getView().dismissSwipeRefresh()
                }

            })
    }

    fun getEmployeeAtPosistion(position: Int): Employee {
        return employeeList?.get(position) ?: Employee()
    }
}