package com.me.employees.employeedetail

import com.me.employees.base.BasePreseneter
import com.me.employees.di.ActivityScope
import com.me.employees.model.Employee
import com.me.employees.service.EmployeeService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class EmployeeDetailPresenter @Inject constructor(employeeService: EmployeeService): BasePreseneter<EmployeeDetailView>() {

    private val employeeService: EmployeeService = employeeService


    fun getEmployeeDetail(employeeId: Int){
        employeeService.getEmployeeDetail(employeeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<Employee>(){
                override fun onComplete() {
                    Timber.i("Employee detail complete block")
                }

                override fun onNext(employee: Employee?) {
                    Timber.i("Employee detail successful response received")
                    getView().updateImage(employee?.base64FullImageString)
                }

                override fun onError(e: Throwable?) {
                    Timber.e("Employee detail error response received $e")
                    getView().displayError()
                }
            })

    }
}